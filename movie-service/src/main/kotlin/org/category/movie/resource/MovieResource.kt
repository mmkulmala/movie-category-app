package org.category.movie.resource

import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import org.jboss.resteasy.annotations.jaxrs.PathParam
import org.category.movie.domain.Movie
import org.category.movie.repository.MovieRepository
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource(val repository: MovieRepository) {

    @POST
    @Transactional
    @Timed(name = "add", unit = MetricUnits.MILLISECONDS)
    fun add(movie: Movie): Response {
        repository.persist(movie)
        return Response.ok(movie).status(201).build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Timed(name = "delete", unit = MetricUnits.MILLISECONDS)
    fun delete(@PathParam id: Long) {
        repository.deleteById(id)
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Timed(name = "update", unit = MetricUnits.MILLISECONDS)
    fun update(@PathParam id: Long, movieChanged: Movie): Response {
        val movie = repository.findById(id)
        movie?.category ?: movieChanged.category
        movie?.movieName ?: movieChanged.movieName
        movie?.origin ?: movieChanged.origin
        movie?.starRate ?: movieChanged.starRate
        movie?.year ?: movieChanged.year

        if (movie != null) {
            repository.persist(movie)
        }
        return Response.ok(movie).status(201).build()
    }

    @GET
    @Timed(name = "findAll", unit = MetricUnits.MILLISECONDS)
    fun findAll(): List<Movie> = repository.listAll()

    @GET
    @Path("/categories/{categories}")
    @Timed(name = "findByCategories", unit = MetricUnits.MILLISECONDS)
    fun findByCategories(@PathParam categories: String): List<Movie> {
        val cats = categories.split(",")
        return repository.findByCategories(cats)
    }
}