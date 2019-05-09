package com.James.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    // repeating sequence with at least 2 characters
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("(..+)\\1");

    // Must be between 5 and 12 characters in length.
    public static boolean checkLength(String password) {
        if (password.length() < 5 || password.length() > 12) {
            return false;
        }
        return true;
    }

    // Must consist of a mixture of lowercase letters and numerical digits only,
    // with at least one of each.
    public static boolean checkCharacter(String password) {
        char[] chars = password.toCharArray();
        boolean num_flag = false;
        boolean lowercase_flag = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num_flag = true;
            }
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                lowercase_flag = true;
            }
        }
        if (num_flag && lowercase_flag) {
            return true;
        }
        return false;
    }

    // Must not contain any sequence of characters immediately followed by the same
    // sequence.
    public static boolean checkSequence(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);

        return !matcher.find();
    }

    // checkSequence in a self-built algorithm way
    public static boolean checkSeqAlgorithm(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            char c1 = pw.charAt(i);
            int nextIndex = pw.indexOf(c1, i + 1);

            while (nextIndex != -1) {
                String s1 = pw.substring(i, nextIndex);
                if (2 * nextIndex - i <= pw.length()) {
                    String s2 = pw.substring(nextIndex, 2 * nextIndex - i);
                    if (s1.equals(s2)) {
                        return false;
                    }
                }
                nextIndex = pw.indexOf(c1, nextIndex + 1);
            }

        }
        return true;
    }


    // public static void main(String[] args) {
    // User user = new User();
    // user.setName("abc");
    // user.setPassword("a131456");
    // verifyPassword(user);
    // }

}
