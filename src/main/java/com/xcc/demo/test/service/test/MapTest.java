package com.xcc.demo.test.service.test;

import com.xcc.comm.util.MapBuilder;

import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Object> innerMap = MapBuilder.<String, Object>newInstance().put("id", 12).build();
        System.out.println(innerMap);
    }
   
}
