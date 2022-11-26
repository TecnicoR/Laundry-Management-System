package com.laundry.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LaundryOrder {
    private Integer id;
    private String userName;
    private Integer numberOfCloths;
    private String clothItems;
    private LocalDate orderDate;
    private LocalDate finishDate;

}
