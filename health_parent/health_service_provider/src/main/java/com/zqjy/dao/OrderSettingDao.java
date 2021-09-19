package com.zqjy.dao;

import com.github.pagehelper.Page;
import com.zqjy.pojo.CheckItem;
import com.zqjy.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);

  public long findCountByOrderDate(Date orderDate);

   public void editNumberByOrderDate(OrderSetting orderSetting);

    public List<OrderSetting> getOrderSettingByMonth(Map date);
    public OrderSetting findByOrderDate(String date);
   public void editReservationsByOrderDate(OrderSetting orderSetting);

   public Page<CheckItem> findAll4Detail(String queryString);
}
