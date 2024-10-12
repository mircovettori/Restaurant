#!/bin/sh
docker run --name restaurantDB -p 5434:5432 -d     -e POSTGRES_PASSWORD=postgres     -e POSTGRES_USER=postgres     -e POSTGRES_DB=exercise --restart unless-stopped  postgres