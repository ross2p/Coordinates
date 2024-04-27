package com.autonetics.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sys {
    int type;
    int id;
    String country;
    int sunrise;
    int sunset;
}