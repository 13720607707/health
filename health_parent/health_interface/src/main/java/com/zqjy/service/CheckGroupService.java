package com.zqjy.service;

import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

   public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

   public List<CheckGroup> findAll();
}
