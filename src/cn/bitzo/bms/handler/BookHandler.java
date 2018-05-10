package cn.bitzo.bms.handler;

import cn.bitzo.bms.entity.Book;
import cn.bitzo.bms.entity.MyResponse;
import cn.bitzo.bms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class BookHandler {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/getAllBooks")
    @ResponseBody
    public MyResponse queryAllBooks() {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        List<Book> books = bookService.queryBooks();
        myResponse.setIsSuccess(true);
        myResponse.setData(books);
        myResponse.setMsg("查询成功");
        return myResponse;
    }

    @RequestMapping(value = "/books/queryById")
    @ResponseBody
    public MyResponse queryBookById(Integer id) {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        Book book= bookService.queryBookById(id);
        myResponse.setIsSuccess(true);
        myResponse.setData(book);
        myResponse.setMsg("查询成功");
        return myResponse;
    }


    @RequestMapping(value = "/books/updateBookInfo")
    @ResponseBody
    public MyResponse updateUserInfo(@RequestBody Book book) {
        MyResponse myResponse = new MyResponse(200, false, "修改失败", null);
        boolean isSuccess = bookService.updateBookInfo(book);
        if (isSuccess) {
            // 配置返回的数据项
            myResponse.setMsg("修改成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/books/addBook")
    @ResponseBody
    public MyResponse addUser(@RequestBody Book book) {
        MyResponse myResponse = new MyResponse(200, false, "添加失败", null);
        int num = bookService.addBook(book);
        if (num > 0) {
            myResponse.setMsg("添加成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/books/getXls")
    public String testExcel(Map<String, Object> map) {
        List<Book> list = new ArrayList<Book>();
        List<Book> books = bookService.queryBooks();
        for (int i = 0; i<books.size();++i) {
            list.add(books.get(i));
        }
        map.put("list", list);
        return "bookExcelView";
    }
}
