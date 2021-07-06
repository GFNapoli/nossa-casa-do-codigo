package br.com.zup.Carros

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated

@Validated
@Controller("/carro")
class CarroController {

    @Post
    fun cadastraVeiculo(carroRequest: CarroRequest): HttpResponse<Any>{

        println(carroRequest)
        return HttpResponse.ok()
    }
}