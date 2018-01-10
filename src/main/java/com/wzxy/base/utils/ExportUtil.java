package com.wzxy.base.utils;

import cn.assist.easydao.pojo.BasePojo;
import cn.assist.easydao.util.PojoHelper;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportUtil
{  
    private XSSFWorkbook wb = null;
  
    private XSSFSheet sheet = null;

    private  static Map<String,Map<Integer,String>> map = new HashMap<String,Map<Integer,String>>();
  
    /** 
     * @param wb 
     * @param sheet 
     */  
    public ExportUtil(XSSFWorkbook wb, XSSFSheet sheet)  
    {  
        this.wb = wb;  
        this.sheet = sheet;  
    }  
  
    /** 
     * 合并单元格后给合并后的单元格加边框 
     *  
     * @param region 
     * @param cs 
     */  
    public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs)
    {  
  
        int toprowNum = region.getFirstRow();  
        for (int i = toprowNum; i <= region.getLastRow(); i++)  
        {  
            XSSFRow row = sheet.getRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++)  
            {  
                XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
                                                // (short) j);  
                cell.setCellStyle(cs);  
            }  
        }  
    }  
  
    /** 
     * 设置表头的单元格样式 
     *  
     * @return 
     */  
    public XSSFCellStyle getHeadStyle()  
    {  
        // 创建单元格样式  
        XSSFCellStyle cellStyle = wb.createCellStyle();  
        // 设置单元格的背景颜色为淡蓝色  
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
        // 设置单元格居中对齐  
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        // 设置单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
        // 创建单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // 设置单元格字体样式  
        XSSFFont font = wb.createFont();  
        // 设置字体加粗  
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);  
        cellStyle.setFont(font);  
        // 设置单元格边框为细线条  
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        return cellStyle;  
    }  
  
    /** 
     * 设置表体的单元格样式 
     *  
     * @return 
     */  
    public XSSFCellStyle getBodyStyle()  
    {  
        // 创建单元格样式  
        XSSFCellStyle cellStyle = wb.createCellStyle();  
        // 设置单元格居中对齐  
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
        // 设置单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
        // 创建单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // 设置单元格字体样式  
        XSSFFont font = wb.createFont();  
        // 设置字体加粗  
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);  
        cellStyle.setFont(font);  
        // 设置单元格边框为细线条  
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
        return cellStyle;  
    }

    public static void exportExcel(Map<String,Map<Integer,String>> maps, String[] titles, String[] names, List<? extends BasePojo> list, ServletOutputStream outputStream) {
        long startTime = System.currentTimeMillis();
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet();
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
//        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
//        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
//            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
//        map.putAll(maps);//公共map数据
        // 构建表体数据
        if (list != null && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                XSSFRow bodyRow = sheet.createRow(j + 1);
                Object object = list.get(j);
                PojoHelper pojoHelper = new PojoHelper(object);
                for (int i = 0; i < names.length; i++){
                    cell = bodyRow.createCell(i);
//                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(getTypeValue(pojoHelper.getMethodValue(names[i]),names[i],maps));
                }
            }
        }
        try {
            System.out.println((System.currentTimeMillis() - startTime)+"毫秒");
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null){
                    outputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String getVaule(Map<Integer,String> map, Integer value){
        for (Map.Entry<Integer,String> m:map.entrySet()) {
            if (m.getKey() == value) {
                return m.getValue();
            }
        }
        return "";
    }

    /**
     * 根据不同类型转成头统一转成字符串类型
     * @param value
     * @param name
     * @return
     */
    private static String getTypeValue(Object value,String name,Map<String,Map<Integer,String>> maps) {
        String textValue = "";
        if (value instanceof Date) {
            Date date = (Date) value;
            textValue = DateUtil.formatDateToYYYYMDHMS(date);
        } else {
            // 其它数据类型都当作字符串简单处理
            textValue = CommonUtil.nullToBlank(value).toString();
        }
        if (maps != null && maps.size() > 0) {
            for (Map.Entry<String,Map<Integer,String>> m:maps.entrySet()) {
                if (m.getKey().equals(name)) {
                    textValue = getVaule(m.getValue(),Integer.parseInt(textValue));
                }
            }
        }
        return textValue;
    }
}  