package com.zqjy.dao;

import com.github.pagehelper.Page;
import com.zqjy.pojo.CheckGroup;
import com.zqjy.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckgroup(Map map);

    public Page<Setmeal> findByCondition(String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

   public List<Map<String, Object>> findSetmealCount();
}
