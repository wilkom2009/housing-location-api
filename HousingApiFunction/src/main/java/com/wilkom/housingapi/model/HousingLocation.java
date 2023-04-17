package com.wilkom.housingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousingLocation {
    Integer id;
    String name;
    String city;
    String state;
    String photo;
    boolean wifi;
    boolean laundry;
}
