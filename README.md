# Spring Boot and Keycloak with OIDC

## Start Keycloak

Make sure Keycloak is running first

```sh
bin/kc.sh start-dev
```

## Start Spring Application

Run `SpringBootKeycloakApplication`

Or:

```sh
mvn clean install
java -jar target/spring-boot-keycloak-0.0.1.jar
```

## Usage

* Navigate to http://localhost:8081
* Login with credentials
* It will redirect you to a welcome screen

---

This project may include third-party open-source code, subject to its original licenses. If there is a licensing concern, please open an issue.

<br>
