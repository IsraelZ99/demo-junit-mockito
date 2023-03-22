package com.demo.demojunitmockito.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "property_title", nullable = false)
    private String title;
    private String description;
    private String ownerName;
    @Column(name = "email", nullable = false)
    private String ownerEmail;
    private Double price;
    private String address;

}
