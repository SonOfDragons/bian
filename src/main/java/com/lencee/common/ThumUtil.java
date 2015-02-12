/**
 * 包名：com.lencee.common
 * 文件名：ThumUtil.java
 * 版本信息：
 * 日期：2014年11月27日-下午4:06:19
 * 
 */
package com.lencee.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

/**
 * 
 * 
 * <p>图片工具类<p>
 * @version 2014年12月17日 下午5:16:22
 *
 */
public class ThumUtil {
	
	 /**
     * 图片缩略图切割 --- 根据文件地址 生成图片缩略图
     * @param fileFromPath 图片源文件地址
     * @param fileOutPath 图片缩略图地址
     * @param outWidth  图片缩略图宽度
     * @param outHeight 图片缩略图高度
     * @param scale 缩放比例
     * @param rotate 旋转度数
     * @return
	 * @throws IOException 
     */
   public static void chreatePictureChange(String fileFromPath,String fileOutPath, Integer outWidth, Integer outHeight, Float scale, Integer rotate) throws IOException {
           Builder<File> builder = (Builder<File>) Thumbnails.of(fileFromPath);
           if (null != outWidth && null == outHeight) {
               builder.width(outWidth);
           }
           if (null == outWidth && null != outHeight) {
               builder.height(outHeight);
           }
           if (null != outWidth && null != outHeight) {
               builder.size(outWidth, outHeight);
           }
           if (null != scale) {
               builder.scale(scale);
           }
           if (null != rotate) {
               builder.rotate(rotate);
           }
           builder.toFile(fileOutPath);
    }

    /**
      * 图片缩略图切割 --- 根据文件流 生成图片缩略图
      * @param fileFrom 图片源文件地址
      * @param fileOutPath 图片缩略图地址
      * @param outWidth  图片缩略图宽度
      * @param outHeight 图片缩略图高度
      * @param scale 缩放比例
      * @param rotate 旋转度数
      * @return
     * @throws IOException 
      */
    public static void chreatePictureChange(InputStream fileFrom,String fileOutPath, Integer outWidth, Integer outHeight, Float scale, Integer rotate) throws IOException {
        try {
            Builder<InputStream> builder = (Builder<InputStream>) Thumbnails.of(fileFrom);
            if (null != outWidth && null == outHeight) {
                builder.width(outWidth);
            }
            if (null == outWidth && null != outHeight) {
                builder.height(outHeight);
            }
            if (null != outWidth && null != outHeight) {
                builder.size(outWidth, outHeight);
            }
            if (null != scale) {
                builder.scale(scale);
            }
            if (null != rotate) {
                builder.rotate(rotate);
            }
            builder.outputQuality(1.0d);
            builder.toFile(fileOutPath);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
         fileFrom.close();
        }
    }

}
