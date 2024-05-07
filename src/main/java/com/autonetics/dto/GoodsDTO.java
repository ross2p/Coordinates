package com.autonetics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsDTO {
    private String name;
    private double priceIn;
    private double priceOut;
    private String category;

    @Override
    public String toString() {
        return "GoodsDTO{" +
                "name='" + name + '\'' +
                ", priceIn=" + priceIn +
                ", priceOut=" + priceOut +
                ", category='" + category + '\'' +
                '}';
    }
}


