package com.thortful.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
final class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String symbol;

    public Stock(){}

    public Stock(String name, String symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }

}
