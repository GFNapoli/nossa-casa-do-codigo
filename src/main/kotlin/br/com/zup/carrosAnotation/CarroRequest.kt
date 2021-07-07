package br.com.zup.carrosAnotation

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CarroRequest(
    @field:NotBlank val modelo: String,
    @field:NotBlank @field:Placa val placa: String
) {
}