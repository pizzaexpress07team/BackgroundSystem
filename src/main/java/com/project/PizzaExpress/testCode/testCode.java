package com.project.PizzaExpress.testCode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.DeliverymanEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class testCode {
    public static void main(String args[]){
        String fileName = "C:/Users/任律达/Desktop/JsonTest1";
        Path path = Paths.get(fileName);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String resString = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("----读取的文件内容----");
            System.out.println(resString);
            JSONObject jsonObject = JSON.parseObject(resString);
            System.out.println("---------json转码后------");
            System.out.println(URLEncoder.encode(jsonObject.toString(), "utf-8"));
            System.out.println("--------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
