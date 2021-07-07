package br.com.zup.carrosAnotation

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/carro")
class CarroController {

    @Post
    @Transactional
    fun cadastraVeiculo(@Body @Valid carroRequest: CarroRequest): HttpResponse<Any>{

        println(carroRequest)
        return HttpResponse.ok()
    }
}