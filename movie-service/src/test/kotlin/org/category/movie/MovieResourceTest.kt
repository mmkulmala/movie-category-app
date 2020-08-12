package org.category.movie

import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.category.movie.domain.Movie

@QuarkusTest
class MovieResourceTest {

    @Test
    fun testAddMovie() {
        val movie = Movie(movieName = "Rambo first Blood", origin = "offline", category = "Drama", starRate = 3, year = 1986)
        given().body(movie).contentType(ContentType.JSON)
                .post("/movies")
                .then()
                .statusCode(201)
    }

    @Test
    fun testAddMoreActionMovies() {
        val names = listOf("Commando", "Ninja 2", "Ninja")
        for (name in names) {
            val movie = Movie(movieName = name, origin = "offline", category = "Action", starRate = 3, year = 1985 + 1)
            given().body(movie).contentType(ContentType.JSON)
                    .post("/movies")
                    .then()
                    .statusCode(201)
        }
    }

    @Test
    fun testAddMovieWithManyCategories() {
        val movie = Movie(movieName = "Rambo second Blood", origin = "offline", category = "Drama,Romance", starRate = 3, year = 1986)
        given().body(movie).contentType(ContentType.JSON)
                .post("/movies")
                .then()
                .statusCode(201)
    }

    @Test
    fun testFindMoviesByCategoryDrama() {
        given().get("/movies/categories/Drama")
                .then()
                .statusCode(200)
                .assertThat().body("size()", `is`(1))
    }

    @Test
    fun testFindMoviesByCategoryRomance() {
        given().get("/movies/categories/Romance")
                .then()
                .statusCode(200)
                .assertThat().body("size()", `is`(1))
    }

    @Test
    fun testFindMoviesByCategoriesRomanceAndAction() {
        given().get("/movies/categories/Romance,Action")
                .then()
                .statusCode(200)
                .assertThat().body("size()", `is`(4))
    }

    @Test
    fun testGetAll() {
        given().get("/movies")
                .then()
                .statusCode(200)
                .assertThat().body("size()", `is`(5))
    }

}