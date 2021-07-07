package br.com.zup.autores

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class AutorControllerTest{

    @field:Inject
    lateinit var repository: AutorRepository

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val endereco = Endereco("111111-111", "123", "rua dos limao", "uberlandia", "Minas gerais")
        autor = Autor("Lucas", "lucas@gmail.com", "Livros de matematica", endereco)
        repository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()
    }

    @Test
    internal fun `deve retornar detalhes de um autor`() {

        val response = client.toBlocking().exchange("/autores?email=${autor.email}", AutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body)

    }

    @Test
    internal fun `deve cadastrar um novo autor`() {

        val autorRequest = AutorRequest("Luiza", "luiza@gmail.com", "livros romanticos", "38408416", "123")
        val enderecoResponse = EnderecoResponse("38408416", "Rua dos passarinhos", "perto da floricultura", "grande arvore", "Uberlandia", "MG", "1234564", "", "34", "1545")

        Mockito.`when`(enderecoClient.buscaPorCep(autorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", autorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }
}