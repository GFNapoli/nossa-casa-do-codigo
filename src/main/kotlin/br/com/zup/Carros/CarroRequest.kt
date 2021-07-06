package br.com.zup.Carros

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CarroRequest(
    @field:NotBlank val modelo: String,
    @field:NotBlank @field:Placa val placa: String
) {
}