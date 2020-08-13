package org.category.movie.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.category.movie.domain.Movie
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieRepository: PanacheRepository<Movie> {

    /**
     * Work around for queries in Kotlin version
     */
    fun findByCategories(categories: List<String>): List<Movie> {
        val movs = emptyList<Movie>().toMutableList()
        for(mov in listAll()) {
            for (cat in categories) {
                if(mov.category.contains(cat)) {
                    movs.add(mov)
                }
            }

        }
        return movs
    }
}