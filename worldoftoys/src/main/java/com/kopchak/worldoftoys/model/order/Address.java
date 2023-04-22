package com.kopchak.worldoftoys.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    @NotBlank(message = "Country is mandatory")
    @Size(max = 64, message = "Country must be up to 64 characters long")
    private String country;

    @Column(length = 100)
    @NotBlank(message = "Country is mandatory")
    @Size(max = 100, message = "Country must be up to 100 characters long")
    private String city;

    @Column(length = 100)
    @NotBlank(message = "Street is mandatory")
    @Size(max = 100, message = "Street must be up to 100 characters long")
    private String street;

    @NonNull
    @Min(value = 1, message = "Building number must be greater than or equal to 1")
    private Integer buildingNumber;

    @Min(value = 1, message = "Flat number must be greater than or equal to 1")
    private Integer flatNumber;
}
