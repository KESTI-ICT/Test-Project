package mgrKesti.utils;

public class StringUtil
{
	public static String nvl(String s){
		return s != null ? s : "";
	}

	public static String nvl(String s, String s1){
		return s != null ? s : s1;
	}
	
}