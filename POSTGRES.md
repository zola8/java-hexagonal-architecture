# Setup PostgreSQL in docker

1. Pull image

    ```
    docker pull postgres
    ```

2. Create instance

    ```
    docker run --rm --name postgres-dev -e POSTGRES_DB=payments -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
    ```

3. Create database

    ```
    psql -h localhost -U postgres -c "CREATE DATABASE payments;"
    ```

   or

    ```sql
    CREATE DATABASE payments;
    ```
