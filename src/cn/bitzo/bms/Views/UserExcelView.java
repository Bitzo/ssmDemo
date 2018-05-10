package cn.bitzo.bms.Views;

import cn.bitzo.bms.entity.User;
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
public class UserExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // 1. 创建excel文档对象
        HSSFWorkbook wb= (HSSFWorkbook) workbook;
        // 2. 创建sheet表单对象
        HSSFSheet sheet = wb.createSheet("users");
        // 3. 创建行对象
        HSSFRow row = sheet.createRow(0);
        // 4. 创建cell单元格
        String[] headers = new String[]{"序号", "姓名", "昵称", "性别", "年龄", "邮箱", "是否有效"};
        for (int i = 0; i < headers.length; ++i) {
            row.createCell(i).setCellValue(headers[i]);
        }
        // 5. 通过map对象获取存储在map中的集合
        List<User> list = (List<User>)map.get("list");
        for (int i = 0; i < list.size(); ++i) {
            User u = list.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(u.getUsername());
            row.createCell(2).setCellValue(u.getNickName());
            row.createCell(3).setCellValue(u.getGender());
            row.createCell(4).setCellValue(u.getAge());
            row.createCell(5).setCellValue(u.getEmail());
            row.createCell(6).setCellValue(u.getIsActive());
        }
        // 6. 起文件名
        String fileName = "users.xls";
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "inline;filename="+new String(fileName.getBytes(), "iso-8859-1"));
        httpServletResponse.setContentType("application-excel");
        OutputStream out = httpServletResponse.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
