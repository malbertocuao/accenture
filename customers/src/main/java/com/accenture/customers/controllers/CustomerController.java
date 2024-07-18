package com.accenture.customers.controllers;

import com.accenture.customers.dto.CustomerDto;
import com.accenture.customers.dto.ErrorResponseDto;
import com.accenture.customers.dto.ResponseDto;
import com.accenture.customers.dto.SupportInfoDto;
import com.accenture.customers.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(
        name = "Controlador de clientes",
        description = "CRUD REST APIs para la creación, recuperación y actualización de clientes"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class CustomerController {

    @NonNull
    private final ICustomerService iCustomerService;

    @Value("${build.version}")
    private String buildVersion;

    @NonNull
    private Environment environment;

    @NonNull
    private SupportInfoDto supportInfo;

    @GetMapping(value = "/support-info")
    public ResponseEntity<SupportInfoDto> supportInfo() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supportInfo);
    }

    @GetMapping(value = "/java-home")
    public ResponseEntity<String> javaVersion() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping(value = "/build-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getBuildInfo() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", buildVersion));
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "Hola desde el microservicio de Clientes";
    }

    @GetMapping(value = "/datetime", produces = MediaType.TEXT_PLAIN_VALUE)
    public String datetime() {
        return LocalDateTime.now().toString();
    }

    @Operation(
            summary = "Creacion de clientes",
            description = "Permite la creacion de clientes en nuestro sistema bancario"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        iCustomerService.createCustomer(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Cliente creado exitosamente !!"));
    }

    @Operation(
            summary = "Consulta de clientes por numero de documento",
            description = "Permite la consulta de clientes utilizando el numero de documento"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                        schema = @Schema(implementation = CustomerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/fetchByDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> fetchByDocument(
            @RequestParam
            @NotEmpty(message = "El campo número de doucmento no puede quedar en blanco")
            @Size(min = 5, max = 20, message = "El documento debe contener entre 5 y 20 caracteres de longitud")
            String document) {

        CustomerDto customerDto = iCustomerService.fetchCustomerByDocument(document);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @GetMapping(value = "/fetchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> fetchByEmail(
            @PathVariable
            @NotEmpty(message = "El campo correo electronico no puede quedar en blanco")
            @Email(message = "Debe especificar un correo electronico valido")
            String email) {

        CustomerDto customerDto = iCustomerService.fetchCustomerByEmail(email);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping(value = "/updateByDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateByDocument(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updated = iCustomerService.updateCustomer(customerDto);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping(value = "/deleteByDocument/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteByDocument(
            @PathVariable
            @NotEmpty(message = "El campo número de doucmento no puede quedar en blanco")
            @Size(min = 5, max = 20, message = "El documento debe contener entre 5 y 20 caracteres de longitud")
            String document) {

        iCustomerService.deleteByDocument(document);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Cliente borrado exitosamente !!"));
    }

}
