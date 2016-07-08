package com.fq.util.file;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExportUtil {

	/**
	 * 根据List<List<String>> data 导出Excel
	 * @param sql
	 * @param out
	 */
	public static void exortExcelBySql (HttpServletResponse response,List<List<String>> data,String fileName)throws Exception{
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName+".xls");
		HSSFWorkbook workBoox = new HSSFWorkbook();
		HSSFSheet sheet = workBoox.createSheet("excel");
		HSSFRow row = sheet.createRow(0); 
		HSSFCellStyle style =workBoox.createCellStyle();  
        style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell ;
        //写入title
        List<String> titles  = data.get(0);
        for (int i = 0 ; i < titles.size() ; i ++){
        	cell = row.createCell(i);
        	cell.setCellValue(titles.get(i));
        	cell.setCellStyle(style);
        }
        HSSFCellStyle centerstyle = workBoox.createCellStyle();   
        centerstyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中   
        centerstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中   
        centerstyle.setWrapText(true);   
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);   
        centerstyle.setBorderLeft((short) 1);   
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);   
        centerstyle.setBorderRight((short) 1);   
        centerstyle.setBorderBottom(CellStyle.BORDER_THIN); // 设置单元格的边框为粗体   
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．   
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色
        //写入数据
        for(int i = 1 ; i < data.size() ; i ++) {
        	  List<String> rows = data.get(i);
        	  row = sheet.createRow(i);
        	  HSSFCell cellData ;
        	  for (int n = 0 ; n < rows.size(); n ++ ){
        		  cellData = row.createCell(n);
        		  cellData.setCellValue(rows.get(n));
        		  cellData.setCellStyle(centerstyle);
        	  }
        }
        workBoox.write(out);  
        out.close();  
	}
}
