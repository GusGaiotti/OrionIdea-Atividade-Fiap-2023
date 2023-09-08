package br.com.fiap.orionidea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsResponse(
    val articles: List<Article>,
    val status: String, // Pode ser uma string representando o status da resposta
    val totalResults: Int // Pode ser um número inteiro representando o total de resultados
)

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    // Outros campos relevantes aos artigos de notícias
)
