package com.sauzny.metting.service;

import com.google.common.collect.Lists;
import com.sauzny.metting.entity.Order;
import com.sauzny.metting.repository.OrderRepository;
import com.sauzny.metting.system.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 9:58
 *
 * @描述: TODO
 *
 ***************************************************************************/
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        order.setIsDeleted(SystemConstant.DB.IS_NOT_LOGIC_DELETE);
        return orderRepository.saveAndFlush(order);
    }

    public Order logicDeleteById(Integer id){

        List<Order> resultBox = new ArrayList<Order>(1);

        Optional<Order> optional = orderRepository.findById(id);

        optional.ifPresentOrElse(
                o -> {
                    o.setIsDeleted(SystemConstant.DB.IS_LOGIC_DELETE);
                    resultBox.add(orderRepository.saveAndFlush(o));
                },
                () -> resultBox.add(null)
        );

        return resultBox.get(0);
    }

    public List<Order> findAllByOrderDate (String date){
        LocalDate orderDate = LocalDate.parse(date);
        return orderRepository.findAllByOrderDateAndIsDeletedOrderByOrderDateDesc(orderDate, SystemConstant.DB.IS_NOT_LOGIC_DELETE);
    }

    public List<Order> findAllByUsername (String username){
        return orderRepository.selectWithUsernameAndIsDeletedOrderByOrderDateDesc(username, SystemConstant.DB.IS_NOT_LOGIC_DELETE);
    }

    public List<Order> findAllbyRoomIdOrderDateRange(Integer roomId, String minDate, String maxDate){
        LocalDate minOrderDate = LocalDate.parse(minDate);
        LocalDate maxOrderDate = LocalDate.parse(maxDate);
        return orderRepository.selectWithRoomidAndOrderDateBetweenAndIsDeletedOrderByOrderDateDesc(roomId, minOrderDate, maxOrderDate, SystemConstant.DB.IS_NOT_LOGIC_DELETE);
    }
}
