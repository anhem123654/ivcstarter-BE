package com.ivcstarter.entity;

import com.ivcstarter.entity.enums.JobLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_titles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // Ví dụ: BUS-1-1-NV-1

    private String name;

    @Enumerated(EnumType.STRING)
    private JobLevel level; // GIAM_DOC, QUAN_LY, NHAN_VIEN, CONG_TAC_VIEN

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String description;
}
