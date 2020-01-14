package com.qinge.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;

/**
 * 
 * <p>
 * Title: KettleJob
 * <p>
 * 
 * <p>
 * Description: 使用JAVA代码执行kettle的Job
 * <p>
 * 
 */
public class KettleJob {
	public static void main(String[] args) {
		String jobPath = "D:\\kettledata\\aa到bb.kjb";
		// 如果有参数可以放到maps里面
		Map<String, String> maps = new HashMap<String, String>();
		runJob(maps, jobPath);
	}

	public static void runJob(Map<String, String> maps, String jobPath) {
		try {
			KettleEnvironment.init();
			// jobname 是Job脚本的路径及名称
			JobMeta jobMeta = new JobMeta(jobPath, null);
			Job job = new Job(null, jobMeta);
			// 向Job 脚本传递参数，脚本中获取参数值：${参数名}
			// job.setVariable(paraname, paravalue);
			Set<Entry<String, String>> set = maps.entrySet();
			for (Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Entry<String, String> ent = it.next();
				job.setVariable(ent.getKey(), ent.getValue());
			}
			job.start();
			job.waitUntilFinished();
			if (job.getErrors() > 0) {
				throw new Exception("There are errors during job exception!(执行job发生异常)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
