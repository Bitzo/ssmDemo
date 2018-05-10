package cn.bitzo.bms.dao;

import cn.bitzo.bms.entity.Borrow;

import java.util.List;

public interface BorrowDao {
    public List<Borrow> queryBorrowInfo();
}
