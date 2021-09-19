package com.zqjy.dao;

import com.github.pagehelper.Page;
import com.zqjy.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);

   public Page<CheckGroup>  findByCondition(String queryString);

    public CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup);

   public void deleteAssocication(Integer id);

    public List<CheckGroup> findAll();
}
