package com.xcc.demo.aop;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.xcc.comm.annotation.Sign;
import com.xcc.demo.test.dao.PdfDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aop")
public class AopTestController {
    @GetMapping(value = "/test")
    public Map<String,Object> test(){
        Map<String,Object> resultMap = new HashMap<>(1);

        resultMap.put("xcc","testAop");
        System.out.println("执行方法------------------------------------------------------"+resultMap.toString());
//        Integer s = null;
//        s.toString();
        return resultMap;
    }

    @GetMapping(value = "/testSign")
    @Sign
    public Map<String,Object> testSign() throws Exception {
        Map<String,Object> resultMap = new HashMap<>(1);
        resultMap.put("xcc","testSign");
        System.out.println("执行方法------------------------------------------------------"+resultMap.toString());
        Integer s = 1;
        if(s == 1){
            throw new Exception("错误");
        }
        return resultMap;
    }

    @PostMapping(value = "/mark")
    @ResponseBody
    public String  selectSiteEntity(@Validated PdfDto dto) {

        // 模板路径
        String templatePath = "D:/a.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:/f.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            form.setField("debentures",dto.getTitle());
            form.setField("debenturesLegalRepresentative",dto.getAge());
            form.setField("debenturesAddress",dto.getName());

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }
        return "";
    }

}
