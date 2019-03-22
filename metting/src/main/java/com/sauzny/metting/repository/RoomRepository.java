package com.sauzny.metting.repository;

import com.sauzny.metting.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 9:48
 *
 * @描述: TODO
 *
 ***************************************************************************/
public interface RoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {

    List<Room> findAllByIsDeleted(int isDeleted);
}
