package br.com.zup.autores

import javax.persistence.*

@Entity
class Endereco(
    val cep: String,
    val numero: String,
    val rua: String,
    val cidade: String,
    val estado: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}