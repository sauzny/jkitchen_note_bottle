package com.sauzny.beantools.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sauzny.beantools.entity.Product;
import com.sauzny.beantools.entity.ProductVO;

// 使用spring的时候需要增加注解中的参数
// @Mapper(componentModel="spring")
@Mapper
public interface ProductMapstruct {

    ProductMapstruct INSTANCE = Mappers.getMapper( ProductMapstruct.class );   
    
    //举例特别字段的转换
    /*
    @Mappings({
        @Mapping(source = "tokenName", target = "tokenName"),
        @Mapping(source = "userOsType", target = "osType"),
        @Mapping(target = "hand",  ignore = true)  
    })
    */
    ProductVO productToProductVO(Product product);
}
