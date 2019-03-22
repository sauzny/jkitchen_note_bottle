package com.sauzny.metting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/***************************************************************************
 *
 * @时间: 2019/3/14 - 17:54
 *
 * @描述: TODO
 *
 ***************************************************************************/
@Getter
@Setter
@Entity
@Table(name="tb_order")
public class Order {

    // 声明主键。
    @Id
    // GenerationType.IDENTITY 主键由数据库自动生成（主要是自动增长型）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String contact;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Room room;
}
