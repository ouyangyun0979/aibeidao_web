package com.uhoo.uhooapi.web.util;

import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class SHA256Coder {
	/**
	 * SHA-224 的消息摘要算法实现
	 * 
	 * @param data
	 *            待处理的数据
	 * @return byte[] 消息摘要
	 * 
	 * */
	public static byte[] encodeSHA224(byte[] data) throws Exception {

		// 加入对bouncy castle的支持
		Security.addProvider(new BouncyCastleProvider());
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// 执行消息摘要处理
		return md.digest(data);
	}

	/**
	 * SHA-224 的消息摘要算法实现
	 * 
	 * @param data
	 *            待处理的数据
	 * @return String 十六进制的消息摘要
	 * @author zcm
	 * */
	public static String encodeSHA224Hex(byte[] data) throws Exception {

		// 执行消息摘要处理
		byte[] b = encodeSHA224(data);

		return new String(Hex.encode(b));
	}
}
