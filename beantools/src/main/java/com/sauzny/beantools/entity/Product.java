package com.sauzny.beantools.entity;

import java.time.LocalDateTime;

import lombok.Data;

public class Product {

    private Long id;
    
    private LocalDateTime createTime;
    
    private LocalDateTime lastUpdateTime;

    private String name;

    private Integer supplierId = 0;

    private String supplierName;

    private String supplierProductNo;

    private Integer purchasePrice = 0;
    
    private Integer minDistribution = 0;
    
    private Integer maxDistribution = 0;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierProductNo() {
        return supplierProductNo;
    }

    public void setSupplierProductNo(String supplierProductNo) {
        this.supplierProductNo = supplierProductNo;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getMinDistribution() {
        return minDistribution;
    }

    public void setMinDistribution(Integer minDistribution) {
        this.minDistribution = minDistribution;
    }

    public Integer getMaxDistribution() {
        return maxDistribution;
    }

    public void setMaxDistribution(Integer maxDistribution) {
        this.maxDistribution = maxDistribution;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    
    
}
