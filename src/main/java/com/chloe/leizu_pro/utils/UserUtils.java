package com.chloe.leizu_pro.utils;

import org.apache.commons.codec.digest.DigestUtils;


public class UserUtils {

    public static String pwdEncode(CharSequence password){
        return DigestUtils.md5Hex(password.toString().getBytes());
    }

    public static boolean pwdMatches(CharSequence password, String encodedPwd){
        return  encodedPwd.equals(pwdEncode(password));
    }
}
