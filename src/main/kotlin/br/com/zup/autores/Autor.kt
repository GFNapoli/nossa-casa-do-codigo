package br.com.zup.autores

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    @field:OneToOne @field:Cascade(CascadeType.ALL) val endereco: Endereco
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    val dataCadastro: LocalDateTime = LocalDateTime.now()
}