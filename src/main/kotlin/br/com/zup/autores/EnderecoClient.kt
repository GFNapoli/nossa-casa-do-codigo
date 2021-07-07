package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json")
    fun buscaPorCep(@PathVariable cep: String): HttpResponse<EnderecoResponse>

    @Get ("/{cep}/xml")//(consumes = [MediaType.APPLICATION_XML], produces = [MediaType.APPLICATION_XML])
    @Consumes(MediaType.APPLICATION_XML) //consumir xml
    //    @Produces(MediaType.APPLICATION_XML)  produzir xml
    fun buscaPorCepXML(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}