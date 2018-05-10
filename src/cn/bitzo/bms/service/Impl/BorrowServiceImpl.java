package cn.bitzo.bms.service.Impl;

import cn.bitzo.bms.dao.BorrowDao;
import cn.bitzo.bms.entity.Borrow;
import cn.bitzo.bms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "borrowService")
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;

    @Override
    public List<Borrow> queryBorrowInfo() {
        List<Borrow> borrows = borrowDao.queryBorrowInfo();
        return borrows;
    }
}
