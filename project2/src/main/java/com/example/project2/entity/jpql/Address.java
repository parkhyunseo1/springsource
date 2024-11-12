package com.example.project2.entity.jpql;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
