package com.fq.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
   
public class PdfUtil {

	/***
	 *iText 生成pdf 文件   wma 20160302
	 */
    public static final String HTML = "C:\\Users/Administrator\\Desktop\\test.html";
    public static final String DEST = "C:\\Users\\Administrator\\Desktop\\test.pdf";
  
 
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        BaseFont chinese = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);  
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), Charset.forName("gb2312"));
        // step 5
        document.close();
    }
  
    /**
     * Main method
     */
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new  PdfUtil().createPdf(DEST);
    }
}