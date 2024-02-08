package com.emreilgar.discountservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gategorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer externalId;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Discount> discounts;
}
