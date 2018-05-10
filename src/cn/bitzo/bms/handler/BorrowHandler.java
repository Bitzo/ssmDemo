package cn.bitzo.bms.handler;

import cn.bitzo.bms.entity.Borrow;
import cn.bitzo.bms.entity.MyResponse;
import cn.bitzo.bms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BorrowHandler {

    @Autowired
    private BorrowService borrowService;

    @RequestMapping(value = "/api/borrow")
    @ResponseBody
    public MyResponse queryBorrowInfo() {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        List<Borrow> borrows = borrowService.queryBorrowInfo();
        myResponse.setIsSuccess(true);
        myResponse.setData(borrows);
        myResponse.setMsg("查询成功");
        return myResponse;
    }
}
