package com.xcc.demo.test.service.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Filetest {

    public static void main(String[] args) throws IOException {

        String s = readFile("C:\\Users\\Administrator\\Desktop\\s.txt",Charset.defaultCharset());

        File file =new File("D:\\2.txt");
        Writer out =new FileWriter(file);

        JSONObject data = JSONObject.parseObject(s);
        Integer supplierId = data.getInteger("supplierId");
        JSONArray details = data.getJSONArray("details");
        for(int i = 0; i<details.size();i++){
            JSONObject detail = details.getJSONObject(i);

            String purNumber = detail.getString("purNumber");
            String fabricId = detail.getString("fabricId");
            StringBuilder result = new StringBuilder();
            result.append("where supplier_id = ").append(supplierId)
                    .append(" and pur_number = '").append(purNumber).append("'")
                    .append(" and fabric_id = '").append(fabricId).append("'").append(" and mark = 0").append("\n");
            out.write(result.toString());

        }
        out.close();
    }



    private static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
