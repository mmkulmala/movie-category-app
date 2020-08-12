package org.category.movie.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
data class Movie(var movieName: String = "",
                 var category: String = "",
                 var starRate: Int = 0,
                 var origin: String = "",
                 var year: Int? = null): PanacheEntity()