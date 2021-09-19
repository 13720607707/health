package com.zqjy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zqjy.dao.CheckitemDao;
import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.pojo.CheckItem;
import com.zqjy.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckitemDao checkitemDao;

    public void add(CheckItem checkItem) {
        checkitemDao.add(checkItem);

    }


    public PageResult pageQuery(QueryPageBean queryPageBean) {
       Integer currentPage= queryPageBean.getCurrentPage();
       Integer pageSize =queryPageBean.getPageSize();
       String queryString =  queryPageBean.getQueryString();
       //基于mybatis分页助手
        PageHelper.startPage(currentPage,pageSize);
       Page<CheckItem> page = checkitemDao.selectByCondition(queryString);
       long total = page.getTotal();
        List<CheckItem> rows = page.getResult();


        return new PageResult(total,rows);
    }

    //根据删除检查项
    public void deleteByid(Integer id) {
        //判断检查项是否已经关联到检查组
        long count = checkitemDao.findCountByCheckItemId(id);
        if(count>0){
            //已关联检查组，不允许删除
            throw  new RuntimeException();
        }
        checkitemDao.deleteById(id);


    }


    public void edit(CheckItem checkItem) {
        checkitemDao.edit(checkItem);

    }


    public CheckItem findById(Integer id) {
        return  checkitemDao.findById(id);
    }


    public List<CheckItem> findAll() {
        return checkitemDao.findAll();
    }

}
