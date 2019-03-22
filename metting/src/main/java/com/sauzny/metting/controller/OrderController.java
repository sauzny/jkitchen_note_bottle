package com.sauzny.metting.controller;

import com.sauzny.metting.controller.vo.RestFulResult;
import com.sauzny.metting.entity.Order;
import com.sauzny.metting.service.OrderService;
import com.sauzny.metting.system.SystemConstant;
import com.sauzny.metting.utils.vo.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 14:56
 *
 * @描述: TODO
 *
 ***************************************************************************/
@RestController
@RequestMapping(value= SystemConstant.Controller.ORDER_CONTROLLER_MAPPING)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public RestFulResult save(@RequestBody Order order){
        orderService.save(order);
        return RestFulResult.success();
    }

    @DeleteMapping("/{id}")
    public RestFulResult cancel(@PathVariable Integer id){
        orderService.logicDeleteById(id);
        return RestFulResult.success();
    }

    // 查询指定日期的预定
    @GetMapping("/date/{orderDate}")
    public RestFulResult findAllbyOrderDate(@PathVariable String orderDate){
        List<Order> orderList = orderService.findAllByOrderDate(orderDate);
        return RestFulResult.success(OrderUtils.orderVOList(orderList));
    }

    // 查询我的预定
    @GetMapping("/my/{username}")
    public RestFulResult myOrder(@PathVariable String username){
        List<Order> orderList = orderService.findAllByUsername(username);
        return RestFulResult.success(OrderUtils.orderVOList(orderList));
    }

    // 查询指定会议室id指定日期范围的预定列表
    @GetMapping("/room/{roomId}/date/{minDate}/{maxDate}")
    public RestFulResult findAllbyRoomIdOrderDateRange(@PathVariable Integer roomId, @PathVariable String minDate, @PathVariable String maxDate){
        List<Order> orderList = orderService.findAllbyRoomIdOrderDateRange(roomId, minDate, maxDate);
        return RestFulResult.success(OrderUtils.orderVOList(orderList));
    }
}
