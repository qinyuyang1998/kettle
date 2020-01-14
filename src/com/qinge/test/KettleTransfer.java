package com.qinge.test;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * 
 * <p>
 * Title: KettleTransfer
 * <p>
 * 
 * <p>
 * Description: 使用JAVA代码执行kettle的转换
 * <p>
 * 
 */
public class KettleTransfer {
	public static void main(String[] args) {
		String transferPath = "D:\\kettledata\\插入更新.ktr";
		// 如果有参数可以放到params里面
		String[] params = {};
		runTransfer(params, transferPath);
	}

	public static void runTransfer(String[] params, String ktrPath) {
		Trans trans = null;
		try {
			// // 初始化
			// 转换元对象
			KettleEnvironment.init();
			// 初始化
			EnvUtil.environmentInit();
			TransMeta transMeta = new TransMeta(ktrPath);
			// 转换
			trans = new Trans(transMeta);
			// 执行转换
			trans.execute(params);
			// 等待转换执行结束
			trans.waitUntilFinished();
			// 抛出异常
			if (trans.getErrors() > 0) {
				throw new Exception("There are errors during transformation exception!(传输过程中发生异常)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
