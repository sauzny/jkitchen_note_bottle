package com.sauzny.metting.service;

import com.sauzny.metting.entity.Room;
import com.sauzny.metting.repository.RoomRepository;
import com.sauzny.metting.system.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room save(Room room) {
        room.setIsDeleted(SystemConstant.DB.IS_NOT_LOGIC_DELETE);
        return roomRepository.saveAndFlush(room);
    }

    public Room update(Room room){
        List<Room> resultBox = new ArrayList<Room>(1);

        Optional<Room> optional = roomRepository.findById(room.getId());

        optional.ifPresentOrElse(
                o -> {
                    o.setName(room.getName());
                    o.setMaxNum(room.getMaxNum());
                    resultBox.add(roomRepository.saveAndFlush(o));
                },
                () -> resultBox.add(null)
        );

        return resultBox.get(0);
    }

    public Room logicDeleteById(Integer id) {

        List<Room> resultBox = new ArrayList<Room>(1);

        Optional<Room> optional = roomRepository.findById(id);

        optional.ifPresentOrElse(
                o -> {
                    o.setIsDeleted(SystemConstant.DB.IS_LOGIC_DELETE);
                    resultBox.add(roomRepository.saveAndFlush(o));
                },
                () -> resultBox.add(null)
        );

        return resultBox.get(0);
    }

    public List<Room> findAllByIsDeleted(){
        return roomRepository.findAllByIsDeleted(SystemConstant.DB.IS_NOT_LOGIC_DELETE);
    }

    public Room findById(Integer id){

        List<Room> resultBox = new ArrayList<Room>(1);

        Optional<Room> optional = roomRepository.findById(id);

        optional.ifPresentOrElse(
                o -> resultBox.add(o),
                () -> resultBox.add(null)
        );

        return resultBox.get(0);
    }
}
