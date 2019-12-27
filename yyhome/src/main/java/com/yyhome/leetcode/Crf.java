package com.yyhome.leetcode;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.jdbc.core.metadata.HanaCallMetaDataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

/**
 * @author miluo
 * @date 2019-11-18
 */
public class Crf {

    public static void main(String[] args) throws IOException {
        doThing();
    }

    public static void doThing() throws IOException {
        // 读取excel
        var excelPath = "/Users/admin/Downloads/预处理.xlsx";
        var excelFile = new FileInputStream(excelPath);
        List<Object> dataList = EasyExcelFactory.read(excelFile, new Sheet(1, 1, CrfExcel.class));
        System.out.println(dataList.get(0));
        Map<String,List<Object>> dataMap = dataList.stream().collect(Collectors.groupingBy(x -> ((CrfExcel) x).getId()));
        System.out.println(dataMap.keySet());
        var contextLastTime = new HashMap<Long,LocalDateTime>(dataMap.size());
        dataMap.forEach((key, value) -> {
            if (!contextLastTime.containsKey(key)){

            }
        });
    }

//    private LocalDateTime convertTime(String str){
//
//    }
}
