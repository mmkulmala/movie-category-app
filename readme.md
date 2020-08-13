# Simple movie categorization app

This is simple movie categorization app done with Quarkus and Kotlin. This now categorizes movies persists, 
but could be used to get movies from IMBD and other sources. While they have the date, this
app could be used to enrich the data.

## Why this solution?

Being interested in Kotlin for couple of years I wanted to create 
something else than a CVBank with it. Quarkus seemed like ideal candidate
after Spring monoliths!

Quarkus seemed really cool and I had to try it my self! With Kotlin I've done
couple of small things and one bigger so learning more Kotlin is always nice.

Data repository was easily enough build with in-memory database H2.
It's easy to use for testing things out and really save (well persist in this case)
to database. Plus you don't have think about real database problems. 

Next things include adding real database and integrating calls to IMDB and Netflix
to get their data either directly or async way saving data to database (basically caching data).
