package com.yyhome.leetcode;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * excel导出工具
 * @source copy from iris
 */
@Slf4j
public class ExcelUtils {

   private static Sheet initSheet;

   static {
      initSheet = new Sheet(1, 0);
      initSheet.setSheetName("sheet");
      //设置自适应宽度
      initSheet.setAutoWidth(Boolean.TRUE);
   }

   /**
    * 读取少于1000行数据
    * @param filePath 文件绝对路径
    * @return
    */
   public static List<Object> readLessThan1000Row(String filePath){
      return readLessThan1000RowBySheet(filePath,null);
   }

   /**
    * 读小于1000行数据, 带样式
    * filePath 文件绝对路径
    * initSheet ：
    *      sheetNo: sheet页码，默认为1
    *      headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
    *      clazz: 返回数据List<Object> 中Object的类名
    */
   public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet){
      if(!StringUtils.hasText(filePath)){
         return null;
      }

      sheet = sheet != null ? sheet : initSheet;

      InputStream fileStream = null;
      try {
         fileStream = new FileInputStream(filePath);
         return EasyExcelFactory.read(fileStream, sheet);
      } catch (FileNotFoundException e) {
         log.info("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(fileStream != null){
               fileStream.close();
            }
         } catch (IOException e) {
            log.info("excel文件读取失败, 失败原因：{}", e);
         }
      }
      return null;
   }

   /**
    * 读大于1000行数据
    * @param filePath 文件觉得路径
    * @return
    */
   public static List<Object> readMoreThan1000Row(String filePath){
      return readMoreThan1000RowBySheet(filePath,null);
   }

   /**
    * 读大于1000行数据, 带样式
    * @param filePath 文件觉得路径
    * @return
    */
   public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet){
      if(!StringUtils.hasText(filePath)){
         return null;
      }

      sheet = sheet != null ? sheet : initSheet;

      InputStream fileStream = null;
      try {
         fileStream = new FileInputStream(filePath);
         ExcelListener excelListener = new ExcelListener();
         EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
         return excelListener.getDatas();
      } catch (FileNotFoundException e) {
         log.error("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(fileStream != null){
               fileStream.close();
            }
         } catch (IOException e) {
            log.error("excel文件读取失败, 失败原因：{}", e);
         }
      }
      return null;
   }

   /**
    * 生成excle
    * @param filePath  绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param data 数据源
    * @param head 表头
    */
   public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head){
      writeSimpleBySheet(filePath,data,head,null);
   }

   /**
    * 生成excle
    * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param data 数据源
    * @param sheet excle页面样式
    * @param head 表头
    */
   public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet){
      sheet = (sheet != null) ? sheet : initSheet;

      if(head != null){
         List<List<String>> list = new ArrayList<>();
         head.forEach(h -> list.add(Collections.singletonList(h)));
         sheet.setHead(list);
      }

      OutputStream outputStream = null;
      ExcelWriter writer = null;
      try {
         outputStream = new FileOutputStream(filePath);
         writer = EasyExcelFactory.getWriter(outputStream);
         writer.write1(data,sheet);
      } catch (FileNotFoundException e) {
         log.error("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(writer != null){
               writer.finish();
            }

            if(outputStream != null){
               outputStream.close();
            }

         } catch (IOException e) {
            log.error("excel文件导出失败, 失败原因：{}", e);
         }
      }

   }

   /**
    * 生成excle
    * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param data 数据源
    */
   public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data){
      writeWithTemplateAndSheet(filePath,data,null);
   }

   /**
    * 生成excle
    * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param data 数据源
    */
   public static void writeWithPath(String filePath, List<? extends BaseRowModel> data){
      writeWithTemplateAndSheet(filePath,data,null);
   }

   /**
    * 生成excle
    * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param data 数据源
    * @param sheet excle页面样式
    */
   public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet){
      if(CollectionUtils.isEmpty(data)){
         return;
      }

      sheet = (sheet != null) ? sheet : initSheet;
      sheet.setClazz(data.get(0).getClass());

      OutputStream outputStream = null;
      ExcelWriter writer = null;
      try {
         outputStream = new FileOutputStream(filePath);
         writer = EasyExcelFactory.getWriter(outputStream);
         writer.write(data,sheet);
      } catch (FileNotFoundException e) {
         log.error("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(writer != null){
               writer.finish();
            }

            if(outputStream != null){
               outputStream.close();
            }
         } catch (IOException e) {
            log.error("excel文件导出失败, 失败原因：{}", e);
         }
      }

   }

   /**
    * 生成多Sheet的excle
    * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
    * @param multipleSheelPropetys
    */
   public static void writeWithMultipleSheel(String filePath,List<MultipleSheelPropety> multipleSheelPropetys){
      if(CollectionUtils.isEmpty(multipleSheelPropetys)){
         return;
      }

      OutputStream outputStream = null;
      ExcelWriter writer = null;
      try {
         outputStream = new FileOutputStream(filePath);
         writer = EasyExcelFactory.getWriter(outputStream);
         for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
            Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
            if(!CollectionUtils.isEmpty(multipleSheelPropety.getData())){
               sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
            }
            writer.write(multipleSheelPropety.getData(), sheet);
         }

      } catch (FileNotFoundException e) {
         log.error("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(writer != null){
               writer.finish();
            }

            if(outputStream != null){
               outputStream.close();
            }
         } catch (IOException e) {
            log.error("excel文件导出失败, 失败原因：{}", e);
         }
      }

   }


   /*********************匿名内部类开始，可以提取出去******************************/

   @Data
   public static class MultipleSheelPropety{

      private List<? extends BaseRowModel> data;

      private Sheet sheet;
   }

   /**
    * 解析监听器，
    * 每解析一行会回调invoke()方法。
    * 整个excel解析结束会执行doAfterAllAnalysed()方法
    */
   @Getter
   @Setter
   public static class ExcelListener extends AnalysisEventListener {

      private List<Object> datas = new ArrayList<>();

      /**
       * 逐行解析
       * object : 当前行的数据
       */
      @Override
      public void invoke(Object object, AnalysisContext context) {
         //当前行
         // context.getCurrentRowNum()
         if (object != null) {
            datas.add(object);
         }
      }


      /**
       * 解析完所有数据后会调用该方法
       */
      @Override
      public void doAfterAllAnalysed(AnalysisContext context) {
         //解析结束销毁不用的资源
      }

   }

   /************************匿名内部类结束，可以提取出去***************************/
   
   /**
    * 使用 模型 来读取Excel
    * @param inputStream Excel的输入流
    * @param clazz 模型的类
    * @param excelTypeEnum Excel的格式(XLS或XLSX)
    * @return 返回 模型 的列表(为object列表,需强转)
    */
   public static List<Object> readExcelWithModel(InputStream inputStream, Class<? extends BaseRowModel> clazz,ExcelTypeEnum excelTypeEnum) {

       // 解析每行结果在listener中处理
	   ExcelListener listener = new ExcelListener();
       ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
       //默认只有一列表头
       excelReader.read(new Sheet(1,1,clazz));
       return  listener.getDatas();

   }
   
   /**
    * 使用模型来读取Excel(XLS或XLSX)
    * @param filePath
    * @param sheet
    * @return
    */
   public static List<Object> readExcelWithModel(String filePath, Sheet sheet){
      if(!StringUtils.hasText(filePath)){
         return null;
      }
      ExcelTypeEnum excelTypeEnum = ExcelTypeEnum.XLSX;
      if(filePath.endsWith(".xls") || filePath.endsWith(".XLS")) {
    	  excelTypeEnum = ExcelTypeEnum.XLS;
      }
      if(filePath.endsWith(".xlsx") || filePath.endsWith(".XLSX")) {
    	  excelTypeEnum = ExcelTypeEnum.XLSX;
      }
      sheet = sheet != null ? sheet : initSheet;
      InputStream inputStream = null;
      try {
    	   inputStream=new FileInputStream(filePath);
    	   // 解析每行结果在listener中处理
    	   ExcelListener listener = new ExcelListener();
	       ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
	       //默认只有一列表头
	       excelReader.read(sheet);
	       return listener.getDatas();
      } catch (FileNotFoundException e) {
         log.error("找不到文件或文件路径错误, 文件：{}", filePath);
      }finally {
         try {
            if(inputStream != null){
            	inputStream.close();
            }
         } catch (IOException e) {
            log.error("excel文件读取失败, 失败原因：{}", e);
         }
      }
      return null;
   }
   
   /**
    * 使用 模型 来写入Excel
    * @param outputStream Excel的输出流
    * @param data 要写入的以 模型 为单位的数据
    * @param table 配置Excel的表的属性
    * @param clazz 模型的类
    * @param excelTypeEnum Excel的格式(XLS或XLSX)
    */
   public static void writeExcelWithModel(OutputStream outputStream, List<? extends BaseRowModel> data, Table table,Class<? extends BaseRowModel> clazz,ExcelTypeEnum excelTypeEnum)  {
       //这里指定需要表头，因为model通常包含表头信息
       ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum,true);
       //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
       Sheet sheet1 = new Sheet(1,0, clazz);
       writer.write(data, sheet1,table);
       writer.finish();
   }
   /**
    * 使用模型导出Excel
    * @Title: writeExcelWithPath
    * @Description: TODO
    * @author chimu
    * @date Jul 19, 2019
    * @param @param path
    * @param @param data
    * @param @param table
    * @param @param clazz
    * @param @param excelTypeEnum 
    * @return void
    * @throws
    */
   public static void writeExcelWithPath(String path, List<? extends BaseRowModel> data,Class<? extends BaseRowModel> clazz,ExcelTypeEnum excelTypeEnum){
	   try {
			OutputStream outputStream=new FileOutputStream(path);
			TableStyle tableStyle=new TableStyle();
            tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);

            //定义Excel正文字体大小
            Font font=new Font();
            font.setFontHeightInPoints((short) 10);
            tableStyle.setTableContentFont(font);

            Table table=new Table(0);
            table.setTableStyle(tableStyle);
			writeExcelWithModel(outputStream, data, table, clazz, excelTypeEnum);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
   }

}

