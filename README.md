# eSports Arena Manager

## Gestión Colaborativa del Proyecto

Trello:
https://trello.com/invite/b/6a1511245ca5f4ad188f2c19/ATTI9da34528964cb7cb581e7631591197c361247407/esports-arena-manager

Se utilizó Trello como herramienta colaborativa para la organización, planificación y seguimiento del desarrollo del proyecto.

---

## Descripción del Proyecto

eSports Arena Manager es una plataforma backend distribuida desarrollada con arquitectura de microservicios utilizando Spring Boot.

El objetivo principal del proyecto es administrar información relacionada con usuarios, equipos, torneos y competiciones dentro del entorno competitivo eSports, utilizando APIs REST para la comunicación entre microservicios.

Actualmente el proyecto se encuentra en desarrollo académico utilizando Java, Maven y Spring Boot.

---

## Integrantes

- Vicente Pizarro
- Kevin Reyes
- Martin Vergara

---

## Tecnologías Utilizadas

- Java 21
- Spring Boot
- Maven
- Spring Data JPA
- Hibernate
- H2 Database
- IntelliJ IDEA
- GitHub
- Postman
- SLF4J

---

## Arquitectura del Proyecto

El proyecto implementa arquitectura distribuida basada en microservicios utilizando estructura CSR:

- Controller
- Service
- Repository

Además se implementaron:

- DTO
- GlobalExceptionHandler
- Bean Validation
- ResponseEntity
- APIs REST
- Logs con SLF4J

---

## Microservicios Implementados

- auth-service
- user-service
- game-service
- team-service
- tournament-service
- registration-service
- result-service
- match-service
- ranking-service
- sanction-service
- notification-service
- prize-service

---

## Funcionalidades Implementadas

### Configuración

- Configuración Spring Boot
- Configuración Maven
- Configuración H2 Database
- Configuración JPA/Hibernate

### Arquitectura

- Arquitectura CSR
- Organización de packages
- Persistencia independiente
- Comunicación REST entre microservicios

### Persistencia

- Creación automática de tablas con Hibernate
- Base de datos en memoria H2
- Repositories con JpaRepository

### Validaciones y Manejo de Errores

- Bean Validation
- GlobalExceptionHandler
- RuntimeException
- ResponseEntity
- Manejo de errores REST

### Logging

- Logs estructurados utilizando SLF4J

---

## Control de Versiones

- Repositorio GitHub
- Control de versiones con Git
- Commits colaborativos
