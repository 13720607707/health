package com.zqjy.service;

import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

   public void add(List<OrderSetting> data);

    public List<Map> getOrderSettingByMonth(String date);

  public  void editNumberByDate(OrderSetting orderSetting);

   public PageResult pageQuery(QueryPageBean queryPageBean);

  public   void deleteByid(Integer id);
}
