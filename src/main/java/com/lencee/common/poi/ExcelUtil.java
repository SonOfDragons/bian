/**
 * 包名：com.lencee.common.poi
 * 文件名：ExcelAnUtil.java
 * 版本信息：
 * 日期：2014年7月18日-上午11:16:14
 * 
 */

package com.lencee.common.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lencee.common.poi.constraints.ExcelHeader;
import com.lencee.common.poi.converts.ExcelDataConvert;

/**
 * 
 * 类名称：ExcelAnUtil
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月18日 上午11:16:14
 * 修改备注：
 * 示例见
 * com.lencee.demo.excel.ExcelDemo
 * @version 1.0.0
 * 
 */

public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	/**
	 * <标题，字段>
	 */
	private Map<String, String> titleFieldMap = new HashMap<String, String>();
	/**
	 * <字段，列号>
	 */
	private Map<String, Integer> fieldColumnMap = new HashMap<String, Integer>();
	/**
	 * <标题，列号>
	 */
	private Map<String, Integer> titleColumnMap = new HashMap<String, Integer>();

	/**
	 * <标题，字段实体>
	 */
	private Map<String,Field> titleFieldobjMap = new  HashMap<String, Field>();
	
	/**
	 * 表头信息是否初始化
	 */
	private boolean isInitHeader = false;

	/**
	 * 第一个空行行号
	 */
	private int emptyRownum = 0;

	private InputStream is;
	private Workbook workbook;
	private String excelFile;
	
	/**
	 * 默认的sheetName
	 */
	private String sheetName = "Sheet";
	
	/**
	 * 传入文件路径对应的文件存在时，是否需要创建新文件覆盖原有文件
	 * true为需要覆盖
	 * false为不需要
	 */
	private boolean isCreatNew = false;

	private boolean ignorHeader = false;

	public ExcelUtil(String fileName) {
		try {
			this.excelFile = fileName;
			
			creatExcel(fileName);
			
			this.is = new FileInputStream(fileName);
			this.workbook = WorkbookFactory.create(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ExcelUtil(String fileName,ExcelProperty excelProperty) {
		try {
			String sheetName = excelProperty.getDefaultSheetName();
			boolean isCreatNew = excelProperty.isCreatNew();
			boolean ignorHeader = excelProperty.isIgnorHeader();
			
			this.excelFile = fileName;
			this.sheetName = sheetName;
			this.isCreatNew = isCreatNew;
			this.ignorHeader = ignorHeader;
			
			creatExcel(fileName);
			
			this.is = new FileInputStream(fileName);
			this.workbook = WorkbookFactory.create(is);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 创建EXCEL格式文件
	 * @param fileName
	 * @return 
	 * @return boolean
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	 */
	
	private void creatExcel(String fileName) throws IOException {
		
		File file = new File(fileName);
		if(file.exists()&&!this.isCreatNew){
			//如果文件已存在，且不需要创建新文件覆盖，直接跳过创建过程
			return;
		}
		fileName=fileName.replaceAll("\\\\", "/");
		int folderflag = fileName.lastIndexOf("/");
		if(folderflag>0){
			String newLocalFile=fileName.substring(0, folderflag);
			File filePath =new File(newLocalFile);
			if(!filePath.isDirectory()){
				filePath.mkdirs();
			}
		}
		
		FileOutputStream fos=new FileOutputStream(file);
		HSSFWorkbook wb=new HSSFWorkbook();//创建工作薄  
		wb.createSheet();//创建工作表
		wb.setSheetName(0, this.sheetName);//设置工作表名  
		wb.write(fos);  
		fos.close();
		
	}

	/**
	 * 将单个对象写入Excel
	 * 
	 * @param sheetName
	 * @param obj
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public void saveToExcel(String sheetName, Object obj) throws IllegalArgumentException, IllegalAccessException, IOException {

		if(StringUtils.isNotBlank(sheetName)){
			this.sheetName = sheetName;
		}
		Sheet sheet = workbook.getSheet(this.sheetName);
		if (null == sheet) {
			sheet = workbook.createSheet(this.sheetName);
		}
		//初始化表头
		initHeader(obj.getClass());
		
		// 解析对象信息，返回对象数据
		Map<String, Field> data = getDataMap(obj);

		if(!this.ignorHeader){
			// 写入表头数据
			if (!isInitHeader) {
				writeHeader(sheet);
			}
		}
		
		// 写入数据
		writeDataToCell(sheet, obj, data);

		// 保存数据，关闭流
		FileOutputStream fileOutStream = new FileOutputStream(excelFile);
		workbook.write(fileOutStream);

		if (fileOutStream != null) {
			fileOutStream.close();
		}
	}

	/**
	 * 将单个对象写入Excel
	 * 
	 * @param sheetName
	 * @param obj
	 * @param outputStream
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public void saveToExcel(String sheetName, Object obj,OutputStream outputStream) throws IllegalArgumentException, IllegalAccessException, IOException {
		if(StringUtils.isNotBlank(sheetName)){
			this.sheetName = sheetName;
		}
		Sheet sheet = workbook.getSheet(this.sheetName);
		if (null == sheet) {
			sheet = workbook.createSheet(this.sheetName);
		}
		//初始化表头
		initHeader(obj.getClass());
		
		// 解析对象信息，返回对象数据
		Map<String, Field> data = getDataMap(obj);

		if(!this.ignorHeader){
			// 写入表头数据
			if (!isInitHeader) {
				writeHeader(sheet);
			}
		}
		
		// 写入数据
		writeDataToCell(sheet, obj, data);

		workbook.write(outputStream);

	}
	/**
	 * 将一组对象写入excel
	 * 
	 * @param <T>
	 * 
	 * @param sheetName
	 * @param list
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public <T> void saveListToExcel(String sheetName, List<T> list) throws IllegalArgumentException, IllegalAccessException, IOException {
		if(StringUtils.isNotBlank(sheetName)){
			this.sheetName = sheetName;
		}
		Sheet sheet = workbook.getSheet(this.sheetName);
		if (null == sheet) {
			sheet = workbook.createSheet(this.sheetName);
		}
		
		initHeader(list.get(0).getClass());
		for (Object obj : list) {
			
			Map<String, Field> data = getDataMap(obj);
			
			if(!this.ignorHeader){
				// 写入表头数据
				if (!isInitHeader) {
					writeHeader(sheet);
				}
			}
			
			// 写入数据
			writeDataToCell(sheet, obj, data);

		}

		// 保存，关闭流
		FileOutputStream fileOutStream = new FileOutputStream(excelFile);
		workbook.write(fileOutStream);
		if (fileOutStream != null) {
			fileOutStream.close();
		}
	}

	/**
	 * 将一组对象写入excel
	 * 
	 * @param sheetName
	 * @param list
	 * @param outputStream 通过输出流将数据写入EXCEL
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public <T> void saveListToExcel(String sheetName, List<T> list,OutputStream outputStream) throws IllegalArgumentException, IllegalAccessException, IOException {
		if(StringUtils.isNotBlank(sheetName)){
			this.sheetName = sheetName;
		}
		Sheet sheet = workbook.getSheet(this.sheetName);
		if (null == sheet) {
			sheet = workbook.createSheet(this.sheetName);
		}
		
		initHeader(list.get(0).getClass());
		for (Object obj : list) {
			
			Map<String, Field> data = getDataMap(obj);
			
			if(!this.ignorHeader){
				// 写入表头数据
				if (!isInitHeader) {
					writeHeader(sheet);
				}
			}
			
			// 写入数据
			writeDataToCell(sheet, obj, data);

		}

		workbook.write(outputStream);
	}
	/**
	 * 初始化表头数据
	 * 
	 * @param sheet
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private void writeHeader(Sheet sheet) {
		// 写入表头数据
		Row row = sheet.getRow(0);
		if (row == null) {
			row = sheet.createRow(0);
		}

		Set<String> titleSet = this.titleColumnMap.keySet();
		for (String title : titleSet) {

			int column = this.titleColumnMap.get(title);

			Cell cell = row.createCell(column);
			cell.setCellValue(title);
			logger.debug("第{}列写入表头{}", column, title);
		}
		isInitHeader = true;
		emptyRownum++;
	}
	/**
	 * 将数据写入excel
	 * 
	 * @param sheet
	 * @param obj
	 * @param data
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private void writeDataToCell(Sheet sheet, Object obj, Map<String, Field> data) {

		Row dataRow = getEmptyRow(sheet);

		Set<String> dataTitleSet = data.keySet();
		for (String title : dataTitleSet) {
			try {
				int column = this.titleColumnMap.get(title);
				Cell dataCell = dataRow.createCell(column);
				// 取字段
				Field field = data.get(title);
				// 取字段值对象
				Object fieldValue = field.get(obj);
				// 将字段数据写入单元格
				setFieldValue(dataCell, fieldValue, field);
				logger.debug("第{}列写入数据{}", column, field);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			}

		}
	}

	/**
	 * 将字段值写入单元格
	 * 
	 * @param cell
	 * @param value
	 * @param type
	 * @param field
	 * @throws ParseException
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private void setFieldValue(Cell cell, Object value, Field field) throws ParseException {
		String fieldType = field.getType().getName();

		if (value == null) {
			return;
		}
		if ("boolean".equals(fieldType) || "java.lang.Boolean".equals(fieldType)) {
			// boolean
			boolean setValue = ExcelDataConvert.convertToBoolean(value);
			cell.setCellValue(setValue);
		} else if ("double".equals(fieldType) || "java.lang.Double".equals(fieldType)) {
			// double
			double setValue = ExcelDataConvert.convertToDouble(value);
			cell.setCellValue(setValue);
		} else if ("java.util.Calendar".equals(fieldType)) {
			// Calendar
			Calendar setValue = (Calendar)value;
			cell.setCellValue(setValue);
		} else if ("java.util.Date".equals(fieldType)) {
			// Date
			Date setValue = (Date) value;
			cell.setCellValue(setValue);
		} else if ("org.apache.poi.ss.usermodel.RichTextString".equals(fieldType)) {
			// RichTextString
		} else {
			// String

			String setValue = String.valueOf(value);

			setValue = ExcelDataConvert.convertToDefinedType(setValue, field);
			cell.setCellValue(setValue);
		}
	}
	/**
	 * 获取最近一个可用空行
	 * 
	 * @param sheet
	 * @return
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private Row getEmptyRow(Sheet sheet) {
		Row row = null;
		int lastRownum = sheet.getLastRowNum();
		logger.debug("lastRownum:{}", lastRownum);
		logger.debug("emptyRownum:{}", emptyRownum);

		if (lastRownum < emptyRownum) {
			row = sheet.createRow(emptyRownum);
		} else {

			for (int i = emptyRownum; i <= lastRownum; i++) {
				Row tempRow = sheet.getRow(i);
				if (tempRow == null) {
					// 空行对象，创建新行后返回
					row = sheet.createRow(i);
					emptyRownum = i;
					break;
				}

				int firstCellnum = tempRow.getFirstCellNum();
				if (firstCellnum < 0) {
					// 无单元格，创建新行后返回
					row = sheet.createRow(i);
					emptyRownum = i;
					break;
				}

				boolean isNullRow = true;
				int lastCellnum = tempRow.getLastCellNum();
				for (int j = firstCellnum; j <= lastCellnum; j++) {
					Cell cell = tempRow.getCell(j);
					String cellValue = cell.getStringCellValue();
					if (StringUtils.isNotBlank(cellValue)) {
						isNullRow = false;
						break;
					}
				}
				if (isNullRow) {
					// 空行，返回
					emptyRownum = i;
					break;
				}
			}

			if (row == null) {
				row = sheet.createRow(lastRownum + 1);
				emptyRownum = lastRownum + 1;
			}
		}
		emptyRownum++;
		logger.debug("returnemptyRownum:{}", emptyRownum);
		logger.info("dataRownum:{}", row.getRowNum());
		return row;
	}

	private Row getNotEmptyRow(Sheet sheet){
		Row row = null;
		int firstrownum = sheet.getFirstRowNum();
		int lastrownum = sheet.getLastRowNum();
		for(int i=firstrownum;i<=lastrownum;i++){
			Row tempRow = sheet.getRow(i);
			if(tempRow!=null){
				int lastCellnum = tempRow.getLastCellNum();
				int firstCellnum = tempRow.getFirstCellNum();
				for (int j = firstCellnum; j <= lastCellnum; j++) {
					Cell cell = tempRow.getCell(j);
					String cellValue = cell.getStringCellValue();
					if (StringUtils.isNotBlank(cellValue)) {
						return tempRow;
					}
				}
			}
		}
		return row;
	}
	/**
	 * 读取指定行
	 * 
	 * @param obj
	 * @param sheetName
	 * @param rownum
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String, String> readLine(Class<?> clazz, String sheetName, int rownum) {
		
		if(StringUtils.isNotBlank(sheetName)){
			this.sheetName = sheetName;
		}
		Sheet sheet = workbook.getSheet(this.sheetName);
		if (null == sheet) {
			sheet = workbook.createSheet(this.sheetName);
		}
		//初始化表头
		initHeader(clazz);
		
		Map<String, String> map = readLine(sheet,rownum);
		
		return map;
	}
	/**
	 * 读取指定行数据
	 * 
	 * @param sheet
	 * @param rownum
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,String> readLine(Sheet sheet,int rownum){
		boolean isEmptyRow = true;
		Map<String, String> map = new HashMap<String, String>();
		Row row = sheet.getRow(rownum);
		
		Set<String> titleset = this.titleColumnMap.keySet();
		for(String title:titleset){
			int column = this.titleColumnMap.get(title);
			Cell cell = row.getCell(column);
			if(cell!=null){
				Field field = this.titleFieldobjMap.get(title);
				String value = getCellValue(cell);
				if(this.titleColumnMap.containsKey(value)&&rownum<1){
					//表头数据，直接返回
					return null;
				}
				try {
					value = ExcelDataConvert.convertToDefinedType(value, field);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(StringUtils.isNotBlank(value)){
					map.put(title, value);
					isEmptyRow = false;
				}
			}
		}
		if(isEmptyRow){
			return null;
		}else{
			return map;
		}
		
	}
	 private String getCellValue(Cell cell) {  
	        String cellValue = "";  
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_STRING:  
	            cellValue = cell.getRichStringCellValue().getString().trim();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:  
	            cellValue = cell.getNumericCellValue()+"";  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
	            break;  
	        case Cell.CELL_TYPE_FORMULA:  
	            cellValue = cell.getCellFormula();  
	            break;  
	        default:  
	            cellValue = "";  
	        }  
	        return cellValue;  
	    }  
	/**
	 * 读取第一行有效数据
	 * 
	 * @param obj
	 * @param sheetName
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,String> readFirstLine(Class<?> clazz, String sheetName){
		Map<String, String> map = new HashMap<String, String>();
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			return null;
		}
		//初始化表头
		initHeader(clazz);
		Row row = getNotEmptyRow(sheet);
		
		Set<String> titleset = this.titleColumnMap.keySet();
		for(String title:titleset){
			int column = this.titleColumnMap.get(title);
			Cell cell = row.getCell(column);
			String value = cell.getStringCellValue();
			map.put(title, value);
		}
		
		return map;
	}
	/**
	 * 读取所有数据
	 * @param clazz
	 * @param sheetName
	 * @return
	 */
	public List<Map<String,String>> readAllData(Class<?> clazz,String sheetName){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			return null;
		}
		//初始化表头
		initHeader(clazz);
		
		int lastRowNum = sheet.getLastRowNum();
		int firstRowNum = sheet.getFirstRowNum();
		for(int i=firstRowNum;i<=lastRowNum;i++){
			Map<String,String> map = readLine(sheet, i);
			if(null!=map){
			list.add(map);
			}
		}
		return list;
	}
	/**
	 * 初始化表头关系
	 * 
	 * @param obj 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	private void initHeader(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			// 取表头配置
			ExcelHeader ed = field.getAnnotation(ExcelHeader.class);

			if (ed != null) {
				String title = ed.title();
				int column = ed.column();
				String fieldname = this.titleFieldMap.get(title);
					if (StringUtils.isBlank(fieldname)) {
						// 初始化表头标题、列号、字段的映射关系
						this.titleFieldMap.put(title, fieldName);
						this.fieldColumnMap.put(fieldName, column);
						this.titleColumnMap.put(title, column);
						this.titleFieldobjMap.put(title, field);
					}
					
			}
		}

	}
	/**
	 * 初始化字段Map
	 * 
	 * @param obj
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	private Map<String, Field> getDataMap(Object obj) {
		// 读取配置注解
		Map<String, Field> titleField = new HashMap<String, Field>();

		Class<? extends Object> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			// 取表头配置
			ExcelHeader ed = field.getAnnotation(ExcelHeader.class);

			if (ed != null) {
				String title = ed.title();
				// 缓存字段，待写入时处理
				titleField.put(title, field);
			}
		}
		return titleField;
	}
}
