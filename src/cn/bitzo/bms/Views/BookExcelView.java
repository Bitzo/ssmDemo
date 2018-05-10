package cn.bitzo.bms.Views;

import cn.bitzo.bms.entity.Book;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Component
public class BookExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // 1. 创建excel文档对象
        HSSFWorkbook wb= (HSSFWorkbook) workbook;
        // 2. 创建sheet表单对象
        HSSFSheet sheet = wb.createSheet("books");
        // 3. 创建行对象
        HSSFRow row = sheet.createRow(0);
        // 4. 创建cell单元格
        String[] headers = new String[]{"序号", "ISBN", "书名", "作者", "价格", "出版社", "库存总量", "库存剩余"};
        for (int i = 0; i < headers.length; ++i) {
            row.createCell(i).setCellValue(headers[i]);
        }
        // 5. 通过map对象获取存储在map中的集合
        List<Book> list = (List<Book>)map.get("list");
        for (int i = 0; i < list.size(); ++i) {
            Book book = list.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(book.getIsbn());
            row.createCell(2).setCellValue(book.getBookName());
            row.createCell(3).setCellValue(book.getAuthor());
            row.createCell(4).setCellValue(book.getPrice());
            row.createCell(5).setCellValue(book.getPublisher());
            row.createCell(6).setCellValue(book.getNumber());
            row.createCell(7).setCellValue(book.getMargin());
        }
        // 6. 起文件名
        String fileName = "books.xls";
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "inline;filename="+new String(fileName.getBytes(), "iso-8859-1"));
        httpServletResponse.setContentType("application-excel");
        OutputStream out = httpServletResponse.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
