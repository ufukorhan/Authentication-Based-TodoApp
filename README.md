# To Do Application

This application is a personal task management application developed to enable users to track and focus on the tasks they need to do.

## Running 

```
docker-compose up
```

## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://tr.linkedin.com/in/ufukorhan)


## Tech Stack

*Server:* Java, Spring, Spring Boot, JPA, Hibernate, Junit, Lombok

*Database:* Couchbase

*CI/CD:* Docker


## Authors

- [@ufukorhan](https://www.github.com/ufukorhan)


## Screenshots

![screencapture-localhost-8080-swagger-ui-index-html-2022-09-17-05_36_28](https://user-images.githubusercontent.com/62377943/190837607-30a74e88-c4c5-4806-9a57-3d0d566335c7.png)

<img width="1473" alt="Ekran Resmi 2022-09-17 06 05 22" src="https://user-images.githubusercontent.com/62377943/190838271-7100adfc-7be8-4930-a843-fb745e8422c7.png">


## API Reference

Note: Open swagger after running the application. You will see "Authorize" button at the right top corner. You can simply paste your token in there and start to use the api. 

- [Swagger-UI](http://localhost:8080/swagger-ui.html)


## Manuel Setup 

- Setting Couchbase

```
docker run -d --name db3 -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase
```

After that, you can access couchbase interface via *http://localhost:8091*

When you open couchbase interface, you have to create a cluster that has username *"Administrator"* password *"password"*

And then create a bucket named *"Todo"*

And create a primary index on this bucket with following query.

```
CREATE PRIMARY INDEX ON `Todo`._default._default;
```

- Running app

```
mvn clean install
mvn spring-boot:run
```

  
