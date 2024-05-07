package com.autonetics.mappers;

import com.autonetics.dto.GoodsDTO;
import com.autonetics.model.Goods;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = "spring"
)
@Component
public interface GoodsMapper {
     Goods toModel(GoodsDTO goodsDTO);
     GoodsDTO toDTO(Goods goods);
}
