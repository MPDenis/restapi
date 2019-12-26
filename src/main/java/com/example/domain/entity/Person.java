package com.example.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @NotNull
    @Column
    private String fullName;

    @Column
    private int age;

    @Column
    private String sex;

    public Person() {
    }

    public Person(String fullName){
        this.fullName = fullName;
    }

    public Person(String fullName, int age, String sex){
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
    }

}
