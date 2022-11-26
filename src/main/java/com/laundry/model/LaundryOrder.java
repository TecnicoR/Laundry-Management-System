package com.laundry.model;

import com.laundry.enums.LaundryOrderStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LaundryOrder {
    private Integer id;
    private String userName;
    private Integer userId;
    private Integer numberOfCloths;
    private String clothItems;
    private LaundryOrderStatus status;
    private LocalDate orderDate;
    private LocalDate finishDate;

}
