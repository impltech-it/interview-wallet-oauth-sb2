package com.impltech.testoauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Size(min = 5, max = 20)
    private String username;

    @Column(name = "password")
    private String password;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Wallet> wallets = new HashSet<>();
}
