package com.cheetah.core.shared;

import java.util.List;

/**
 * Utility class providing various utilities.
 * 
 * @author Marcel
 */
public class Util {

	public final static String HTML_LT = "&lt;";
	public final static String STRING_LT = "<";
	public final static String HTML_GT = "&gt;";
	public final static String STRING_GT = ">";
	public final static String HTML_CR = "<br>";
	public final static String STRING_CR = "\n";

	public static String toString(String string) {
		if (string != null) {
			string = string.replaceAll(HTML_LT, STRING_LT);
			string = string.replaceAll(HTML_GT, STRING_GT);
			string = string.replaceAll(HTML_CR, STRING_CR);
		}
		return string;
	}

	public static String escapeHtmlTags(String string) {
		if (string != null) {
			string = string.replaceAll(STRING_LT, HTML_LT);
			string = string.replaceAll(STRING_GT, HTML_GT);
		}
		return string;
	}

	public static String listToString(List<String> list) {
		if (list == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(",    ");
			}
		}
		return sb.toString();
	}

}
