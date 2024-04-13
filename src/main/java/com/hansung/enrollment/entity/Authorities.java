package com.hansung.enrollment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUTHORITIES")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {

    @Id
    private String username;

    private String authority;
}

