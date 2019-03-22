package com.sauzny.metting.utils.vo;

import com.google.common.collect.Lists;
import com.sauzny.metting.controller.vo.OrderVO;
import com.sauzny.metting.entity.Order;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/3/22 - 9:39
 *
 * @描述: TODO
 *
 ***************************************************************************/
public class OrderUtils {

    private OrderUtils(){}

    private static BeanCopier copier1 = BeanCopier.create(Order.class, OrderVO.class, false);

    public static OrderVO orderVO(Order order){
        OrderVO orderVO = new OrderVO();
        copier1.copy(order, orderVO, null);
        orderVO.setRoomName(order.getRoom().getName());
        return orderVO;
    }

    public static List<OrderVO> orderVOList(List<Order> orderList){
        List<OrderVO> orderVOList = Lists.newArrayList();
        orderList.forEach(order -> orderVOList.add(orderVO(order)));
        return orderVOList;
    }
}
