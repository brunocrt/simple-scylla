# Simple ScyllaDB (aka. Cassandra) test Application

This simple project shows how to test a simple scyllaDB NoSQL database using docker
and Java application using DataStax driver.

## Requirements

- Java 8
- Maven 3
- Docker

## Steps

1. Start the scyllaDB docker container
`docker run --name scylla -p 9042:9042 -v data:/var/lib/scylla --hostname scylla -d scylladb/scylla`

2. Wait the start (seeing the logs)
`

3. Connect to the CQL shell console and
`docker exec -it scylla cqlsh`

4. Create the keyspace (inside CQL shell)
`CREATE KEYSPACE IF NOT EXISTS test WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 } AND DURABLE_WRITES = true;`

5. Create the table (inside CQL shell)
`USE test`
`CREATE TABLE IF NOT EXISTS users (
    email      text,
    firstname  text,
    lastname   text,
    PRIMARY KEY (email)
    );`

7. Insert some data

`INSERT INTO users (email, firstname, lastname) VALUES ('u1@sample.com', 'User1', 'Lastname1')`
`INSERT INTO users (email, firstname, lastname) VALUES ('u2@sample.com', 'User2', 'Lastname2')`
`INSERT INTO users (email, firstname, lastname) VALUES ('u3@sample.com', 'User3', 'Lastname3')`

6. Build the application
`mvn clean package`

7. Run the Application
`java -jar target\simple-scylla-1.0-SNAPSHOT.jar Lastname3`