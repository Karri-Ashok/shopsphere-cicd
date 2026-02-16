package com.shopsphere.cartservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
	    name = "cart_items",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"userEmail", "productId"})
	)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private Long productId;

    private Integer quantity;
}
