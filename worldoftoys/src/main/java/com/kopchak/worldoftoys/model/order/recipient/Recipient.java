package com.kopchak.worldoftoys.model.order.recipient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60)
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 2, max = 60, message = "Firstname must be up to 60 characters long")
    private String firstname;

    @Column(length = 65)
    @NotBlank(message = "Patronymic is mandatory")
    @Size(min = 5, max = 65, message = "Patronymic must be up to 65 characters long")
    private String patronymic;

    @Column(length = 60)
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 60, message = "Lastname must be up to 60 characters long")
    private String lastname;

    @NonNull
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;


    @Column(length = 9)
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 9, max = 9, message = "Phone number must be 9 characters long")
    private String phoneNumber;
}
