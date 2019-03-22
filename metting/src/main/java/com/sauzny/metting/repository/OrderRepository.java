package com.sauzny.metting.repository;

import com.sauzny.metting.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 9:51
 *
 * @描述: TODO
 *
 ***************************************************************************/
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    List<Order> findAllByOrderDateAndIsDeletedOrderByOrderDateDesc(LocalDate orderDate, int isDeleted);

    //List<Order> findAllByUsernameAndIsDeletedOrderByOrderDateDesc(String username, int isDeleted);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.room t WHERE o.username = ?1 and o.isDeleted=?2 ORDER BY o.orderDate DESC")
    List<Order> selectWithUsernameAndIsDeletedOrderByOrderDateDesc(String username, int isDeleted);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.room t WHERE o.roomId = ?1 and o.orderDate >= ?2 and o.orderDate <= ?3 and o.isDeleted=?4 ORDER BY o.orderDate ASC")
    List<Order> selectWithRoomidAndOrderDateBetweenAndIsDeletedOrderByOrderDateDesc(int roomId, LocalDate minDate, LocalDate maxDate, int isDeleted);
}
