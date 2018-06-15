package com.sauzny.beantools.convert;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.sauzny.beantools.entity.Product;
import com.sauzny.beantools.entity.ProductVO;

public final class ProductDozer {

    static Mapper mapper = new DozerBeanMapper(); 
    
    public static ProductVO productToProductVO(Product product){ 
        return mapper.map(product, ProductVO.class);  
    }
}
