package com.accenture.gatewayserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/callToSupport")
    public Mono<String> callToSupport() {
        return Mono.just("Ha ocurrido un error en el microservicio. Contacte a soporte técnico");
    }

}
