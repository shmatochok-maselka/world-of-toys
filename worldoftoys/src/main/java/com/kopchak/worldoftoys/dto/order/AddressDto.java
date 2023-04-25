package com.kopchak.worldoftoys.dto.order;

import com.kopchak.worldoftoys.model.order.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Order delivery address")
public class AddressDto {
    @Schema(example = "Україна", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Country is mandatory")
    @Size(max = 64, message = "Country must be up to 64 characters long")
    private String country;

    @Schema(example = "Закарпатська", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Region is mandatory")
    @Size(max = 100, message = "Region must be up to 100 characters long")
    private String region;

    @Schema(example = "Ужгород", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Settlement is mandatory")
    @Size(max = 100, message = "Settlement must be up to 100 characters long")
    private String settlement;

    @Schema(example = "Грушевського", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Street is mandatory")
    @Size(max = 100, message = "Street must be up to 100 characters long")
    private String street;

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NonNull
    @Min(value = 1, message = "Building number must be greater than or equal to 1")
    private Integer buildingNumber;

    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Min(value = 1, message = "Flat number must be greater than or equal to 1")
    private Integer flatNumber;

    public Address toAddress() {
        return Address
                .builder()
                .country(this.country)
                .region(this.region)
                .settlement(this.settlement)
                .street(this.street)
                .buildingNumber(this.buildingNumber)
                .flatNumber(this.flatNumber)
                .build();
    }
}
