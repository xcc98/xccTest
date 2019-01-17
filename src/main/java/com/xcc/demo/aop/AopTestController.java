package com.xcc.demo.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aop")
public class AopTestController {
    @GetMapping(value = "/test")
    public Map<String,Object> test(){
        Map<String,Object> resultMap = new HashMap<>(1);

        resultMap.put("xcc",1222);
        System.out.println("执行方法------------------------------------------------------"+resultMap.toString());
//        Integer s = null;
//        s.equals(2);
        return resultMap;
    }
}
