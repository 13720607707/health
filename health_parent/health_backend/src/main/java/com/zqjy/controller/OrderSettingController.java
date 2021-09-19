package com.zqjy.controller;
//预约设置

import com.alibaba.dubbo.config.annotation.Reference;
import com.zqjy.constant.MessageConstant;
import com.zqjy.entity.PageResult;
import com.zqjy.entity.QueryPageBean;
import com.zqjy.entity.Result;
import com.zqjy.pojo.CheckItem;
import com.zqjy.pojo.Order;
import com.zqjy.pojo.OrderSetting;
import com.zqjy.service.OrderService;
import com.zqjy.service.OrderSettingService;
import com.zqjy.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    @Reference
    private OrderService orderService;
    //x新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody Map map) {
        try {
            map.put("orderType", Order.ORDERTYPE_TELEPHONE);
            Result result = orderService.order(
                    map
            );
            return result;

        } catch (Exception e) {

            e.printStackTrace();
            return new Result(false, "预约失败");
        }


    }

    //实现预约数据批量导入
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile execelFile) {
        try {
            List<String[]> list = POIUtils.readExcel(execelFile);
            List<OrderSetting> data = new ArrayList<>();
            for (String[] strings : list) {
                String orderDate = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderDate), Integer.parseInt(number));
                data.add(orderSetting);

            }

            //将数据保存到数据库
            orderSettingService.add(data);
        } catch (IOException e) {
            e.printStackTrace();
            //文件解析失败
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    //根据月份查询对应的预约设置数据
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) {//参数格式为：2019-03
        try {
            System.out.println(date);
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            //获取预约设置数据成功
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            //获取预约设置数据失败
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //根据月份查询对应的预约设置数据
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {//参数格式为：2019-03
        try {
            orderSettingService.editNumberByDate(orderSetting);
            //获取预约设置数据成功
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //获取预约设置数据失败
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = orderSettingService.pageQuery(queryPageBean);

        return pageResult;


    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            orderSettingService.deleteByid(
                    id
            );
            return new Result(true,"取消预约成功");

        } catch (RuntimeException e) {

            return new Result(false, e.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            return new Result(false, "取消预约失败");
        }


    }
}
