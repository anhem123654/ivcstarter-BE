package com.ivcstarter.entity;

import jakarta.persistence.*;
import lombok.*;
import com.ivcstarter.entity.enums.DepartmentCode;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DepartmentCode code; // BUS, MKT, HRM

    private String name;

    private String description;
}
