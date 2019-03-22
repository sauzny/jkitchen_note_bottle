package com.sauzny.metting.controller.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

/***************************************************************************
 *
 * @时间: 2019/3/22 - 9:18
 *
 * @描述: TODO
 *
 ***************************************************************************/
@Getter
@Setter
public class OrderVO {

    private Integer id;

    private String username;

    private String contact;

    private String roomName;

    private LocalDate orderDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
