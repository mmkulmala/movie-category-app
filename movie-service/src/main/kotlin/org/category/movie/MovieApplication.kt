package org.category.movie

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application

@OpenAPIDefinition(info = Info(title = "Movie Category API", version = "1.0.0"))
class MovieApplication: Application()