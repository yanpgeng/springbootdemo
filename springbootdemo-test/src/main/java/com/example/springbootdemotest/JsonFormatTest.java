package com.example.springbootdemotest;


import com.google.gson.Gson;

import java.util.*;

/**
 * @author YangPeng
 * @Title: JsonFormatTest
 * @ProjectName springbootdemo
 * @Description: TODO
 * @company ccxcredit
 * @date 2019/7/31-9:38
 */
public class JsonFormatTest {
    public static void main(String[] args) {
        List<Map<String,String>> map = new ArrayList<>();
        List<Map<String,String>> mapList = new ArrayList<>();
        for(int i =0;i<10;i++){
            Map<String,String> map1 = new HashMap<>();
            Map<String,String> map2 = new LinkedHashMap<>();
            map1.put("cashingDate","2019-08-09");
            map1.put("trancheA","0");
            map1.put("trancheB","0");
            map1.put("trancheC","0");
            map2.put("cashingDate","2019-08-09");
            map2.put("trancheA","0");
            map2.put("trancheB","0");
            map2.put("trancheC","0");
            map.add(map1);
            mapList.add(map2);
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
        System.out.println(gson.toJson(mapList));
        //格式如下：
//        [{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"},{"cashingDate":"2019-08-09","trancheB":"0","trancheA":"0","trancheC":"0"}]

    }
}
