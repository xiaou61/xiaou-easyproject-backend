package com.xiaou.xiaoueasyprojectbackend.module.support.AIGC.utils;



import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * 功能：
 * 作者：小曾曾
 * 日期：2024/6/17 14:42
 */
public class AiUtils {
    public static String nlp(String text){
        //设置APPID/AK/SK
        String APP_ID = "83587380";
        String API_KEY = "SejKA5DC3EQSZjaiF3nBvrVJ";
        String SECRET_KEY = "zLy6BFZN3JizljtMgtT0lnWmH8KJyuq8";

        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        org.json.JSONObject res = client.ecnet(text, null);
        String res1 = res.getJSONObject("item").getString("correct_query");
        return res1;
    }
    public static String word(MultipartFile file) throws IOException {
        //设置APPID/AK/SK
        String APP_ID = "83580023";
        String API_KEY = "0urM8I7uzI7BzZ9lvjJuyuC5";
        String SECRET_KEY = "1NZgFzChNR8YHYNdvYZwdZ49HBO8uj5P";

        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 调用接口
//        String path = "G:\\1.png";
        JSONObject res = client.basicGeneral(file.getBytes(), new HashMap<String, String>());
        JSONArray jsonArray = res.getJSONArray("words_result");
        StringBuffer txt = new StringBuffer();
        for(int i=0;i<jsonArray.length();i++){
            txt.append(jsonArray.getJSONObject(i).getString("words")).append("\n");
        }
        return txt.toString();
    }
}