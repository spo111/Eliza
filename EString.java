
/*************************************************************************
 * Name:  Yulian Zhou, Zheng Kuang
 * Email: zhouyulian17@gmail.com, kuangzheng04@gmail.com
 *
 * Compilation:  javac EString.java
 * Execution:    none
 * Dependencies: none
 *
 * Description:  This class provides different methods to process an input 
 *               String. 
 * Adapted from http://www.chayden.net/eliza/Eliza.html
 *
 *************************************************************************/

public final class EString {
	
	private final static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";      // upper letters A-Z
	private final static String LOWER = "abcdefghijklmnopqrstuvwxyz";      // lower letters a-z
	private final static String MARK  = "@#$%^&*()_-+=~`{[}]|:;<>\\\"";    // all special marks
	private final static String SPACE = "                          ";      // white space
	private final static String BREAK = ",?!";                             // break marks for sentence
	private final static String STOP  = "...";                             // periods
	private final static String NUM = "0123456789";                        // all the digits 0-9
	
	/**
	 * Process input String s.
	 * (1) converts letters from upper case to lower case
	 * (2) converts each special mark to white space
	 * (3) compresses s by dropping space before space, comma, and period
	 * (4) adds space before question
	 */
	public static String process(String s) {
		for (int i = 0; i < UPPER.length(); i++) {
			s = s.replace(UPPER.charAt(i), LOWER.charAt(i));
		}
		for (int i = 0; i < MARK.length(); i++) {
			s = s.replace(MARK.charAt(i), SPACE.charAt(i));
		}
		for (int i = 0; i < BREAK.length(); i++) {
			s = s.replace(BREAK.charAt(i), STOP.charAt(i));
		}
		String t = "";
		if (s.length() != 0) {
			char c = s.charAt(0);
			for (int i = 1; i < s.length(); i++) {
				char d = s.charAt(i);
				if (c == ' ' && (d == ' ' || d == ',' || d == '.')) continue;
				if (c != ' ' && d == '?') t += c + " ";
				else                      t += c;
				c = d;
			}
			t += c;
		}
		return t;
	}
	
	/**
	 * Trims off leading space of String s.
	 */
	public static String trim(String s) {
        for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') return s.substring(i);
		}
		return "";
	}
	
	/**
	 * Adds white space before and after String s.
	 */
	public static String addPad(String s) {
		if (s.length() == 0) return " ";
		char f = s.charAt(0);
		char l = s.charAt(s.length() - 1);
		if (f != ' ' && l != ' ') return " " + s + " ";
		if (f == ' ' && l != ' ') return s + " ";
		if (f != ' ' && l == ' ') return " " + s;
		return s;
	}
	
	/**
	 * Match input String s against pattern String pat, and fills in 
	 * array lines[] with subString of s that matched * and #.
	 */
	public static boolean match(String s, String pat, String lines[]) {
		int i = 0;    // pointer on s
		int j = 0;    // pointer on pat
		int k = 0;    // pointer on lines[]
		while (j < pat.length() && k < lines.length) {
			char c = pat.charAt(j);
			if (c == '*') {
				// try match pattern of s against pat after *; return false if no match found
				// store String before match in lines[], and increment k
				// move i to the start of match; move j to the start of pattern after *
				int n;
				if (j == pat.length() - 1)  n = s.length() - i;
				else n = patSearch(s.substring(i), pat.substring(j + 1));                    
				if (n < 0)  return false;
				lines[k++] = s.substring(i, i + n);
				i += n;
				j++;
			}
			else if (c == '#') {
				// move i to the first char after any number, store number String in lines[]
				// increment j, k
				int n = findNum(s.substring(i));
				lines[k++] = s.substring(i, i + n);
				i += n;
				j++;
			}
			else {
				// match pattern of s against pat
				// move i, j to the first char after the match
				int n = findMatch(s.substring(i), pat.substring(j));
				if (n <= 0) return false;
				i += n;
				j += n;
			}
		}
		if (i > s.length() - 1 && j > pat.length() - 1) return true;
		return false;
	}
	
	/**
	 * Returns the index of the first non-number char on a String s.
	 */
	private static int findNum(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (NUM.indexOf(s.charAt(i)) == -1) return i;
		}
		return s.length();
	}

	/**
	 * Searches an input String s against a pattern String pat;
	 * Returns the index of s, from which s starts to match with pat;
	 * Returns -1 if not match.
	 */
	private static int patSearch(String s, String pat) {
		for (int i = 0; i < s.length(); i++) {
			if (findMatch(s.substring(i), pat) >= 0) return i;
		}
		return -1;
	}
    
	/**
	 * Match the beginning of an input String s against a pattern String pat;
	 * Returns number of matching chars before * or # if match;
	 * Otherwise returns -1.
	 */
	private static int findMatch(String s, String pat) {
		int i = 0;
		while (i < s.length() && i < pat.length()) {
			char c = pat.charAt(i);
			if (c == '*' || c == '#') return i;
			if (s.charAt(i) != c) return -1;
			i++;
		}
		return i;
	}
}