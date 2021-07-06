package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import io.netty.handler.codec.http.HttpExpectationFailedEvent
import javax.transaction.Transactional

import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val repository: AutorRepository, val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: AutorRequest): HttpResponse<Any>{

        println(request)
        val endereco = enderecoClient.buscaPorCep(request.cep)

        if (endereco.body() == null){
            return HttpResponse.badRequest()
        }

        val autor = request.paraAutor(endereco.body()!!)
        repository.save(autor)
        println(autor.nome)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }

    //autore?email=xxx@xxx.com
    @Get
    @Transactional
    fun listaDeAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any>{

        if(email.isBlank()){
            val lista = repository.findAll()

            val response = lista.map { autor -> AutorResponse(autor) }
            return HttpResponse.ok(response)
        }

        var possivelAutor = repository.findByEmail(email)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(AutorResponse(possivelAutor.get()))
    }

    @Put("/{id}")
    @Transactional
    fun atualizaAutor(@PathVariable id: Long, descricao: String): HttpResponse<Any>{
        val possivelAutor = repository.findById(id)

        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(AutorResponse(autor))
    }

    @Delete("/{id}")
    @Transactional
    fun deletaAutor(@PathVariable id: Long): HttpResponse<Any>{
        val possivelAutor = repository.findById(id)

        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        //repository.delete(possivelAutor.get())
        repository.deleteById(id)
        return  HttpResponse.ok()
    }
}
