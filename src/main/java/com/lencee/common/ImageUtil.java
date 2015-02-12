package com.lencee.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;

public class ImageUtil {

	/**
	 * 生成图片缩略图（动态图的缩略图只能生成静态图片）
	 * @param srcPath	源图片路径
	 * @param destPath	目的图片路径
	 * @param thumWidth	缩略图宽度
	 * @param thumHight	缩略图长度
	 * @return
	 * @throws IOException
	 */
	public static boolean scale(String srcPath,String destPath,int thumWidth,int thumHight) throws IOException{
		File src = new File(srcPath);
		File dest = new File(destPath);
		int beginIndex = destPath.lastIndexOf(".")+1;
		String type = destPath.substring(beginIndex);
		boolean flag = scale(src,dest,thumWidth, thumHight, type);
		return flag;
	}
	
	/**
	 * 生成图片缩略图（动态图的缩略图只能生成静态图片）
	 * @param srcPath	源图片路径
	 * @param destPath	目的图片路径
	 * @param thumWidth	缩略图宽度
	 * @param thumHight	缩略图长度
	 * @param type		缩略图类型
	 * @return
	 * @throws IOException
	 */
	public static boolean scale(File src,File dest,int thumWidth,int thumHight,String type) throws IOException{
		BufferedImage apples = ImageIO.read(src);
		ResampleOp ro = new ResampleOp(DimensionConstrain.createMaxDimension(thumWidth, thumHight,true));
		BufferedImage bi = ro.filter(apples, null);
		ImageIO.write(bi, type, dest);
		return true;
	}
	

}