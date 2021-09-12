package com.school.finediningserver.model;

import javax.persistence.*;

// Spring security class
//@Entity
//@Table(name = "authorities", schema = "public")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, unique = true)
//    private Long id;
//
//    @Column(name = "user_role")
//    private String name;
//
//}
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}