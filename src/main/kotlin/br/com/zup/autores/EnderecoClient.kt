package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("")
interface EnderecoClient {

    @Get //(consumes = [MediaType.APPLICATION_XML], produces = [MediaType.APPLICATION_XML])
//    @Consumes(MediaType.APPLICATION_XML)  consumir xml
//    @Produces(MediaType.APPLICATION_XML)  produzir xml
    fun buscaPorCep(@QueryValue cep: String): HttpResponse<EnderecoResponse>
}