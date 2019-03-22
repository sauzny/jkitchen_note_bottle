package com.sauzny.metting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 9:38
 *
 * @描述: TODO
 *
 ***************************************************************************/

@Getter
@Setter
@Entity
@Table(name="tb_room")
public class Room {

    // 声明主键。
    @Id
    // GenerationType.IDENTITY 主键由数据库自动生成（主要是自动增长型）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "max_num")
    private Integer maxNum;

    @Column(name = "is_deleted")
    private Integer isDeleted;
}
