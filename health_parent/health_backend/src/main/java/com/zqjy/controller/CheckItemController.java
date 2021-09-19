package com.zqjy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zqjy.constant.MessageConstant;
import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.entity.Result;
import com.zqjy.pojo.CheckItem;
import com.zqjy.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    //x新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(
                    checkItem
            );
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

        } catch (Exception e) {

            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }


    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);

        return pageResult;


    }

    //删除检查项
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')")
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkItemService.deleteByid(
                    id
            );
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);

        } catch (RuntimeException e){

            return new Result(false,e.getMessage());
        }catch (Exception e) {

            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }

    }
    //编辑检查项
    @RequestMapping("/edit")
    public Result delete(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(
                    checkItem
            );
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);

        }catch (Exception e) {

            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }

    }
    //查
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
          CheckItem checkItem=  checkItemService.findById(
                    id
            );
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);

        }catch (Exception e) {

            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
    //查所有
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
           List<CheckItem> list =checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);

        }catch (Exception e) {

            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }
}
