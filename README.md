# Java API - Banking Homepage
Projeto baseado em [Santander Dev Week 2023 Java API](https://github.com/digitalinnovationone/santander-dev-week-2023-api)

RESTful API construída em Java 17 com Spring Boot 3.

## Principais Tecnologias

   - Java 17
   - Spring Boot 3
   - Spring Data JPA
   - OpenAPI (Swagger)
   - Railway

## Diagrama de Classes

```mermaid
classDiagram
    class User {
        - name: String
        - account: Account
        - features: Feature[]
        - card: Card
        - news: News[]
    }

    class Account {
        - number: String
        - agency: String
        - balance: Float
        - limit: Float
    }

    class Feature {
        - icon: String
        - description: String
    }

    class Card {
        - number: String
        - limit: Float
    }

    class News {
        - icon: String
        - description: String
    }

    User "1" *-- "1" Account
    User "1" *-- "N" Feature
    User "1" *-- "1" Card
    User "1" *-- "N" News
```