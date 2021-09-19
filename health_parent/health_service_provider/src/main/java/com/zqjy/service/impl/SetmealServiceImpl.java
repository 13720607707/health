package com.zqjy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqjy.constant.RedisConstant;
import com.zqjy.dao.SetmealDao;
import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.Setmeal;
import com.zqjy.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;
    //新增套餐信息，同时观察检查组
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId =setmeal.getId();
        this.setSetmealAndCheckgroup(setmealId,checkgroupIds);
        //将图片名称保存到Redis集合当中
        String fileName =setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,fileName);


    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage =queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);

       Page<Setmeal> page=setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public List<Setmeal> findAll() {
        List<Setmeal> list=setmealDao.findAll();
        return list;
    }
//套餐详情
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }
//查询套餐预约占比
    public List<Map<String, Object>> findSetmealCount() {
      List<Map<String,Object>>  list= setmealDao.findSetmealCount();
        return list ;
    }

    //设置套餐和检查组多对多关系，操作t_setmeal_checkgroup
    public void setSetmealAndCheckgroup(Integer setmealId,Integer[] checkgroupIds){
        if(checkgroupIds!=null&&checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String,Integer> map =new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setSetmealAndCheckgroup(map);

                
            }
        }
    }
}
