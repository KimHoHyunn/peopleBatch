package com.people.batch.util;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
	
	//private FileUtil() {}
	
	public static final class PATH {
		public static final String CONFIG 	= "config";
		public static final String TSA 		= "tsa";
		public static final String IDX 		= "idx";
		public static final String IMAGE 	= "img";
		public static final String CARD		= "card";
	}
	
	public static final class EXT {
		public static final String TSA 		= ".tsa";
		public static final String IDX 		= ".idx";
		public static final String IMAGE 	= ".tif";
		public static final String INF 		= ".inf";
	}
	
	public static boolean exists(File file) {
		return null != file && file.exists();
	}
	
	public static boolean exists(String file) {
		return null != file && exists(new File(file));
	}
	
	public static boolean isFile(File file) {
		//canRead는 별도 추가한것인데.. 이상하거나 중복된 것이면 빼도 됨.
		return exists(file) && file.canRead() && file.isFile();
	}
	
	/**
	 * 경로 문자열들을 경로로 조합.
	 * @param first
	 * @param more
	 * @return
	 */
	
	public static File joinPaths(String first, String ... more) {
		File file = new File(first);
		for(String path : more) {
			file = new File(file, path);
		}
		
		return file;
	}


}
