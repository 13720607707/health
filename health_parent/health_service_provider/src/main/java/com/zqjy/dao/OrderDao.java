package com.zqjy.dao;

import com.github.pagehelper.Page;
import com.zqjy.pojo.CheckItem;
import com.zqjy.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();

    public Page<CheckItem> findAll4Detail(String queryString);

    public void deleteByid(int id);
}
