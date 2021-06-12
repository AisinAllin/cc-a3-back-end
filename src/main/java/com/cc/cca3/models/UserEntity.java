package com.cc.cca3.models;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private String uuid;

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<MusicalInstrument> musicalInstruments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<Cart> carts;
}
