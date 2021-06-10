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
@Table(name = "musical_instrument")
public class MusicalInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Type(type = "long")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @Column(unique = true)
    private String type;

    @Column
    private String name;

    @Column(name = "num_left")
    private Long numLeft;

    @Column
    private Float price;

    @Column
    private Long count;

    @Column
    private Boolean added;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "musicalInstrument", cascade = CascadeType.ALL)
    private Set<Cart> carts;
}
