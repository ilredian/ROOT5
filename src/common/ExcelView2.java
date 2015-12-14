package common;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class ExcelView2 extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> modal, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=cheatDBTable.xls");	

        OutputStream os = null;
        InputStream is = null;

        try {
            // 엑셀 템플릿 파일이 존재하는 위치 (cxlasspath 하위)
//        	AbstractApplicationContext context  = new ClassPathXmlApplicationContext("/cheat.xls");
            is = new ClassPathResource("/cheatTable.xls").getInputStream();
            os = response.getOutputStream();

            XLSTransformer transformer = new XLSTransformer();

            Workbook excel = transformer.transformXLS(is, modal);
            excel.write(os);
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(os != null) try { os.close(); } catch (IOException e) { }
            if(is != null) try { is.close(); } catch (IOException e) { }
        }
    }
}