package com.zqjy.service;

import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(QueryPageBean queryPageBean);

   public void deleteByid(Integer id);

  public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);

   public List<CheckItem> findAll();
}
