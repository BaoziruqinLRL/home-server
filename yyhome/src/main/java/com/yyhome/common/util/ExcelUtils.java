package com.yyhome.common.util;

import com.alibaba.excel.EasyExcel;
import com.yyhome.data.bo.TestExcelBO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author miluo
 * @date 2020-01-14
 */
public class ExcelUtils {

    public static <T> List<T> readExcel(String path, Class<T> destClass) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (path == null){
            throw new FileNotFoundException("Not exist empty file by empty path");
        }
        try (InputStream in = new FileInputStream(path)) {
            var listener = new ExcelListener<T>();
            EasyExcel.read(in,destClass,listener).sheet().doRead();
            return listener.getData();
        }
    }

    public static void main(String[] args){
        try {
            try {
                List<TestExcelBO> res = ExcelUtils.readExcel("/Users/admin/Downloads/crm_commit_test.xlsx", TestExcelBO.class);
                System.out.println(res.get(0).getUserId());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
