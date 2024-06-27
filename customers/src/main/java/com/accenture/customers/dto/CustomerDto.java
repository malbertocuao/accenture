package com.accenture.customers.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Esquema para almacenar los datos de los clientes"
)
public class CustomerDto {

    @Schema(
            description = "Numero del documento de identificacion del cliente"
    )
    @NotEmpty(message = "El campo número de doucmento no puede quedar en blanco")
    @Size(min = 5, max = 20, message = "El documento debe contener entre 5 y 20 caracteres de longitud")
    private String document;

    @Schema(
            description = "Nombre del cliente"
    )
    @NotEmpty(message = "El campo nombre no puede quedar en blanco")
    @Size(min = 3, max = 80, message = "El nombre debe contener entre 5 y 20 caracteres de longitud")
    private String name;

    @Schema(
            description = "Correo electronico del cliente"
    )
    @NotEmpty(message = "El campo correo electronico no puede quedar en blanco")
    @Email(message = "Debe especificar un correo electronico valido")
    private String email;

    @Schema(
            description = "Numero de telefono del cliente"
    )
    @NotEmpty(message = "El campo numero de telefono no puede quedar en blanco")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "El numero de telefono debe contener 10 digitos")
    private String phone;

    @Schema(
            description = "Direccion del cliente"
    )
    @Size(min = 20, max = 150, message = "La direccion debe contener entre 20 y 150 caracteres de longitud")
    private String address;
}
