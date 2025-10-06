package com.ivcstarter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activation_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeCode;

    @Column(nullable = false, unique = true)
    private String encodedKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_role_id")
    private Role targetRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_department_id")
    private Department targetDepartment;

    @Column(name = "target_job_title")
    private String targetJobTitle;

    @Column(name = "is_used")
    private boolean isUsed = false;

    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryTime;

    private boolean isActivated = false;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalDateTime createdAt = LocalDateTime.now();
}