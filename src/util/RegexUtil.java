package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	
	public static List<String> getEmail(String str){
		List<String> es = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\w\\.-]*\\w@[\\w\\.-]*\\w+\\.\\w{2,5}");
		Matcher m = p.matcher(str);
		while(m.find()){
			es.add(m.group());
		}
		return es;
	}
	
	public static List<String> getLink(String str){
		List<String> es = new ArrayList<String>();
		Pattern p = Pattern.compile("<a.*?\\s+href=['\"]?([^'\">]*?)['\"]?.*?>(.*?)</a>");
		Matcher m = p.matcher(str);
		while(m.find()){
			es.add(m.group(1));
		}
		return es;
	}
}
