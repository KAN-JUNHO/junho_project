package com.book.demo.vo;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Pay {
    private String bookname;
    private Date paymentdate;
    private int price;


}
