package com.sauzny.beantools.convert;

import org.springframework.cglib.beans.BeanCopier;

import com.sauzny.beantools.entity.Product;
import com.sauzny.beantools.entity.ProductVO;

public final class ProductBeanCopier {

    static BeanCopier copier = BeanCopier.create(Product.class, ProductVO.class, false);
    
    private ProductBeanCopier(){}
    
    public static void productToProductVO(Product product, ProductVO productVO){
        copier.copy(product, productVO, null);
    }
}
