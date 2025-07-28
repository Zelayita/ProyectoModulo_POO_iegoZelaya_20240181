package DiegoZelaya_20240181.DiegoZelaya_20240181.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DTOProvedor {


    private Long providerID;

    @NotBlank(message = "El Nombre no puede ser nulo")
    private String providerName;

    private String providerPhone;

    private String providerAddress;
    @Email(message = "Debe ser un correo Valido")
    private String Email;

    private String providerCode;

    private Long providerStatus;

    private String providerComments;
}
