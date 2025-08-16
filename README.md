# Spring Boot + Tomcat + MySQL 開発環境 (Docker 利用)

このリポジトリは **Java (Spring Boot) + Tomcat + MySQL** の開発環境を **Docker** で構築するためのテンプレートです。

- Spring Boot(Java, Maven)
- Apache Tomcat(Webserver)
- MySQL(Database)

---

## 📂 ディレクトリ構成
```
.
├── docker
│   └── mysql
│       ├── Dockerfile
│       └── my.cnf
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java/...
│   │   └── resources
│   │       ├── application.properties
│   │       ├── application.yml
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java/...
└── wrapper
    └── maven-wrapper.properties
```

---

## ⚙️ 前提条件

- Docker
- Docker Compose