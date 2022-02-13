package pers.justin.preselectioncourses.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Justin on 2022-02-12 19:45
 */
public class Md5Util {

    /**
     * 加密方法
     *
     * @param password 密码
     */
    public static String EncoderByMd5(String password) {
        String encryptStr = null;
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            //加密后的字符串
            encryptStr = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encryptStr;
    }

    public static Boolean checkPassword(String newPassword, String oldPassword) {
        return EncoderByMd5(newPassword).equals(oldPassword);
    }
}
