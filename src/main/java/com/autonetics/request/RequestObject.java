package com.autonetics.request;

import com.autonetics.model.Coordinates;
import com.autonetics.model.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestObject {
    private Goods goods;
    private Coordinates coordinates;

}