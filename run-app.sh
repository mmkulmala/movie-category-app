#!/usr/bin/env bash

cd movie-service
mvn compile quarkus:dev

# now goto http://0.0.0.0:8080/swagger-ui/