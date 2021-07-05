package br.com.zup.autores

data class AutorRequest(
    val nome: String,
    val email: String,
    val descricao: String
) {
    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}