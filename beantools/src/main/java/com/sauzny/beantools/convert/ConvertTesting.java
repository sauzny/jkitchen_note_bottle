package com.sauzny.beantools.convert;

import java.time.LocalDateTime;

import org.springframework.util.StopWatch;

import com.sauzny.beantools.entity.Product;
import com.sauzny.beantools.entity.ProductVO;

public class ConvertTesting {

    public static void main(String[] args) {
        
        Product product = new Product();
        product.setId(9869879L);
        product.setName("name");
        product.setSupplierProductNo("SupplierProductNo");
        product.setSupplierName("SupplierName");
        product.setPurchasePrice(123324);
        product.setMinDistribution(8172638);
        product.setMaxDistribution(12425345);
        
        // dozer不支持LocalDateTime
        // 其他两种方式都支持
        //product.setCreateTime(LocalDateTime.now());
        //product.setLastUpdateTime(LocalDateTime.now());
        
        
        ProductVO mapstructResult = ProductMapstruct.INSTANCE.productToProductVO(product);
        ProductVO dozerResult = ProductDozer.productToProductVO(product);

        ProductVO beanCopierResult = new ProductVO();
        ProductBeanCopier.productToProductVO(product, beanCopierResult);
        
        System.out.println(mapstructResult);
        System.out.println(dozerResult);
        System.out.println(beanCopierResult);

        StopWatch clock100 = new StopWatch("100次");
        clock100.start("mapstruct");
        for(int i=0;i<100;i++) ProductMapstruct.INSTANCE.productToProductVO(product);
        clock100.stop();
        clock100.start("dozer");
        for(int i=0;i<100;i++) ProductDozer.productToProductVO(product);
        clock100.stop();
        clock100.start("beanCopier");
        for(int i=0;i<100;i++) ProductBeanCopier.productToProductVO(product, new ProductVO());
        clock100.stop();
        System.out.println(clock100.prettyPrint());
        

        StopWatch clock10000 = new StopWatch("10000次");
        clock10000.start("mapstruct");
        for(int i=0;i<10000;i++) ProductMapstruct.INSTANCE.productToProductVO(product);
        clock10000.stop();
        clock10000.start("dozer");
        for(int i=0;i<10000;i++) ProductDozer.productToProductVO(product);
        clock10000.stop();
        clock10000.start("beanCopier");
        for(int i=0;i<10000;i++) ProductBeanCopier.productToProductVO(product, new ProductVO());
        clock10000.stop();
        System.out.println(clock10000.prettyPrint());
        

        StopWatch clock1000000 = new StopWatch("1000000次");
        clock1000000.start("mapstruct");
        for(int i=0;i<1000000;i++) ProductMapstruct.INSTANCE.productToProductVO(product);
        clock1000000.stop();
        clock1000000.start("dozer");
        for(int i=0;i<1000000;i++) ProductDozer.productToProductVO(product);
        clock1000000.stop();
        clock1000000.start("beanCopier");
        for(int i=0;i<1000000;i++) ProductBeanCopier.productToProductVO(product, new ProductVO());
        clock1000000.stop();
        System.out.println(clock1000000.prettyPrint());
    }
}
