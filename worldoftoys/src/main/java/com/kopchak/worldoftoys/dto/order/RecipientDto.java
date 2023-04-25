package com.kopchak.worldoftoys.dto.order;

import com.kopchak.worldoftoys.model.order.CountryCode;
import com.kopchak.worldoftoys.model.order.Recipient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Information about the recipient of the parcel")
public class RecipientDto {
    @Schema(example = "Ірина", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 2, max = 60, message = "Firstname must be up to 60 characters long")
    private String firstname;

    @Schema(example = "Сергіївна", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Patronymic is mandatory")
    @Size(min = 5, max = 65, message = "Patronymic must be up to 65 characters long")
    private String patronymic;

    @Schema(example = "Копчак", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 60, message = "Lastname must be up to 60 characters long")
    private String lastname;

    @Schema(example = "UA", requiredMode = Schema.RequiredMode.REQUIRED)
    @NonNull
    private CountryCode countryCode;

    @Schema(example = "950123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 9, max = 9, message = "Phone number must be 9 characters long")
    private String phoneNumber;

    public Recipient toRecipient(){
        return Recipient
                .builder()
                .firstname(this.firstname)
                .patronymic(this.patronymic)
                .lastname(this.lastname)
                .countryCode(this.countryCode)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
