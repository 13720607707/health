package com.zqjy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zqjy.constant.MessageConstant;
import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.entity.Result;
import com.zqjy.pojo.CheckGroup;
import com.zqjy.service.CheckGroupService;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
    //新增检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try{
        checkGroupService.add(checkGroup,checkitemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();;
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }


    }
    //根据Id查询检查组
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckGroup checkGroup=checkGroupService.findById(id);
            System.out.println("-------------------");
            System.out.println(checkGroup);
            System.out.println("-----------------------------------------------------");
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch (Exception e){
            e.printStackTrace();;
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }


    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

        return   checkGroupService.pageQuery(queryPageBean);



    }
    //根据Id查询检查组包含多个检查项Id
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){

        try{
            List<Integer> checkitemids=checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemids);
        }catch (Exception e){
            e.printStackTrace();;
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }






    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try{
            checkGroupService.edit(checkGroup,checkitemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();;
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }


    }
    //查询所有检查组
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
          List<CheckGroup>  list =  checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();;
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }


    }
}
