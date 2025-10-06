package com.ivcstarter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private int level;

    private String source; // Nguồn
    private String fullName;
    private String firstName;
    private String salutation; // Danh xưng
    private String style; // Phong cách

    private String phone1;
    private String phone2;
    private String address;
    private String project;
    private String segment;
    private String type;
    private String saving; // Tiết kiệm
    private String need; // Nhu cầu
    private double finance; // Tài chính
    private String relationship;
    private LocalDate latest;
    private int availableDays; // Ngày trống
    private String status;
    private String interact;
    private String nextAction;
    private String interactionTime;
    private String consultationMethod;
    @Column(length = 1000)
    private String needDescription;
    @Column(length = 1000)
    private String customerPersona;
    @Column(length = 1000)
    private String note;
    private LocalDate birthYear;
    private String zaloUid;
    private String region;
}
