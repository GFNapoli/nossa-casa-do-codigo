package br.com.zup.autores

import java.time.LocalDateTime

class Autor(
    val nome: String,
    val email: String,
    val descricao: String
) {
    val id: Long? = null
    val dataCadastro: LocalDateTime = LocalDateTime.now()
}