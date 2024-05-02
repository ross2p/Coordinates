package com.autonetics.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Goods {
    private String name;
    private double priceIn;
    private double priceOut;
    private double priceAI;
    private String category;

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", priceIn=" + priceIn +
                ", priceOut=" + priceOut +
                ", category='" + category + '\'' +
                '}';
    }
}
