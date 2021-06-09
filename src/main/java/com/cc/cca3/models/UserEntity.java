package com.cc.cca3.models;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_info")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Type(type = "long")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String status;

    @Column
    private String phone;

    @Column
    private String password;

}
