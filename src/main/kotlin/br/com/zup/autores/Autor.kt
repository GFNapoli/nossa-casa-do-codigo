package br.com.zup.autores

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    val dataCadastro: LocalDateTime = LocalDateTime.now()
}