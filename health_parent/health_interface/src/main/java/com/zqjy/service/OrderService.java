package com.zqjy.service;

import com.zqjy.entity.Result;

import java.util.Map;

public interface OrderService {
    public Result order(Map map) throws Exception;

   public Map findById(Integer id);
}
