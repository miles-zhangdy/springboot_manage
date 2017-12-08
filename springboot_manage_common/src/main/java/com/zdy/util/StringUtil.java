package com.zdy.util;

/**
 * 瀛楃涓插伐鍏风被
 * @author 
 *
 */
public class StringUtil {

	/**
	 * 鍒ゆ柇鏄惁鏄┖
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 鍒ゆ柇鏄惁涓嶆槸绌�	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String formatLike(String str){
		if(isNotEmpty(str)){
			return "%"+str+"%";
		}else{
			return null;
		}
	}
}
