package com.sauzny.metting.controller;

import com.sauzny.metting.controller.vo.RestFulResult;
import com.sauzny.metting.entity.Room;
import com.sauzny.metting.service.RoomService;
import com.sauzny.metting.system.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 15:19
 *
 * @描述: TODO
 *
 ***************************************************************************/
@RestController
@RequestMapping(value= SystemConstant.Controller.ROOM_CONTROLLER_MAPPING)
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public RestFulResult save(@RequestBody Room room){
        roomService.save(room);
        return RestFulResult.success();
    }

    @PutMapping
    public RestFulResult update(@RequestBody Room room){
        roomService.update(room);
        return RestFulResult.success();
    }

    @DeleteMapping("/{id}")
    public RestFulResult cancel(@PathVariable Integer id){
        roomService.logicDeleteById(id);
        return RestFulResult.success();
    }

    @GetMapping
    public RestFulResult list(){
        return RestFulResult.success(roomService.findAllByIsDeleted());
    }

    @GetMapping("/{id}")
    public RestFulResult one(@PathVariable Integer id){
        return RestFulResult.success(roomService.findById(id));
    }
}
