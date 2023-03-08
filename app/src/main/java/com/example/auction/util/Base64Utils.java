package com.example.auction.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;

import java.io.IOException;

/**
 * 使用Base64来保存和获取密码数据
 */
public class Base64Utils {


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decryptBASE64(String key) {
        int decodetime = 5;//压缩和解压的次数，防止被简单破解
        byte[] bt;
        key = key.trim().replace(" ", "");//去掉空格
        while (decodetime > 0) {
            bt = (Base64.getDecoder()).decode(key);
            key = new String(bt);
            decodetime--;
        }

        return key;//如果出现乱码可以改成： String(bt, "utf-8")或 gbk
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encryptBASE64(String key) {
        int decodetime = 5;//压缩和解压的次数，防止被简单破解
        byte[] bt = null;
        key = key.trim().replace(" ", "");//去掉空格
        while (decodetime > 0) {
            bt = key.getBytes();
            key = (Base64.getEncoder()).encodeToString(bt);
            decodetime--;
        }

        return key;
    }
}
