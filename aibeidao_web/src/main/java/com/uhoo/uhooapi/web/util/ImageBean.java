/**
 *@Project：Liangyicity
 *@Title:ImageBean.java
 *@Package:com.lyc.util
 *@Decription:TODO
 *@author:邓忠林 dglin 
 *@date：2013-11-7 下午11:04:59
 *@copyright:2013
 *@version:V1.0
 */
package com.uhoo.uhooapi.web.util;

import java.awt.image.BufferedImage;

/**
 * @ClassName:ImageBean
 * @Description:图片验证码的模型层
 * @author:邓忠林 dglin
 * @date 2013-11-7
 */

public class ImageBean {
	private String codestr;
	private BufferedImage image;

	public String getCodestr() {
		return codestr;
	}

	public void setCodestr(String codestr) {
		this.codestr = codestr;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
