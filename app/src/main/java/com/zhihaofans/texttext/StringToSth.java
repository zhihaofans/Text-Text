package com.zhihaofans.texttext;

import java.security.MessageDigest;

/**
 * Created by zhihaofans on 2016/12/20.
 */
//这里是ASCII与字符串互转
public class StringToSth {
    public static String AsciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
    public static String StringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }
    public static String UnicodeToString(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
    public static String StringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
    public static String MessageDigestEncode(String type,String txt) {
        try{
            MessageDigest md = MessageDigest.getInstance(type);
            md.update(txt.getBytes());    //问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
            StringBuffer buf=new StringBuffer();
            for(byte b:md.digest()){
                buf.append(String.format("%02x", b&0xff));
            }
            return  buf.toString();
        }catch( Exception e ){
            e.printStackTrace();

            return null;
        }
    }
    public static String md5(String txt) {
        return MessageDigestEncode("MD5",txt);
    }
    public static String sha1(String txt) {
        return MessageDigestEncode("SHA1",txt);
    }
    public static String sha224(String txt) {
        return MessageDigestEncode("SHA224",txt);
    }
}