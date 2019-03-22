package com.sauzny.metting.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.sauzny.metting.utils.JacksonUtils;
import lombok.Data;

import static com.sauzny.metting.system.SystemConstant.*;
import static com.sauzny.metting.system.SystemConstant.Result.*;

@Data
@JsonInclude(Include.NON_NULL)
public class RestFulResult {

    private Integer code;
    private String message;
    private Object object;
    
    public static RestFulResult success(){
        RestFulResult result = new RestFulResult();
        result.setCode(STATUS_SUCCESS);
        result.setMessage(MESSAGE_SUCCESS);
        return result;
    }
    
    public static RestFulResult success(Object object){
        RestFulResult result = RestFulResult.success();
        result.setObject(object);
        return result;
    }

    
    public static RestFulResult failure(){
        RestFulResult result = new RestFulResult();
        result.setCode(STATUS_FAILURE);
        result.setMessage(MESSAGE_FAILURE);
        return result;
    }
    
    public static RestFulResult failure(FailureEnum failureEnum){
        RestFulResult result = new RestFulResult();
        result.setCode(failureEnum.getStatusCode());
        result.setMessage(failureEnum.getMessage());
        return result;
    }
    
    public static RestFulResult failure(Integer status, String message){
        RestFulResult result = new RestFulResult();
        result.setCode(status);
        result.setMessage(message);
        return result;
    }
    
    public String toJson(){
        return JacksonUtils.nonNull().toJson(this);
    }
}
