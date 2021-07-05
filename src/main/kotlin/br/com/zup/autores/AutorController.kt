package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import io.netty.handler.codec.http.HttpExpectationFailedEvent

import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(val repository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: AutorRequest): HttpResponse<Any>{

        println(request)

        val autor = request.paraAutor()
        repository.save(autor)
        println(autor.nome)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }

    @Get
    fun listaDeAutores(): HttpResponse<List<AutorResponse>>{

        val lista = repository.findAll()

        val response = lista.map { autor -> AutorResponse(autor) }
        return HttpResponse.ok(response)
    }

    @Put("/{id}")
    fun atualizaAutor(@PathVariable id: Long, @Body descricao: String): HttpResponse<Any>{
        val possivelAutor = repository.findById(id)

        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao
        repository.update(autor)

        return HttpResponse.ok(AutorResponse(autor))
    }

    @Delete("/{id}")
    fun deletaAutor(@PathVariable id: Long): HttpResponse<Any>{
        val possivelAutor = repository.findById(id)

        if (possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        repository.delete(possivelAutor.get())
        return  HttpResponse.ok()
    }
}
