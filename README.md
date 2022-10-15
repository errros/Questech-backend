# About

A school management RESTful APi for staff, teachers and students as well as an E-Learn platform.

## To do
- Add a JWT refresh token to the authentication system
- Integrate a Messaging System

## What I learned from the experience

- Firsthand experience with Spring boot framwork with it diffrent Starters (Spring Security , Spring Data JPA  , Spring Web....)
- Experience with Java tools amd libraries such as Maven , Lombok , Apache Poi , Java Mail ...
- Creating a RESTful Api 
- JWT authentication and Role based authorization
- Documenting an API adhering to the OpenAPI specification (SpringDoc implementation)
- Team work and dealing with front-end developers in a small work team (5 members) (https://github.com/yamanidev/Questech-frontend)

## Project requirements

- Spring Boot  version >= 2.6
- MySQL version >= 8 

## Running the project locally

1. `mvn install` to install the project's dependencies and build it
2. Create a database named elearn_DB ,change the datasource username and password in application.properties file in the resources package
3. run the the project from the IDE
## Documentation
after running the server , go to  http://127.0.0.1:8080/swagger-ui/index.html
and type / in the swagger searchbar to test the endpoints, the db is already filled with 3 default users :
   1. email : admin@admin.com , password : admin
   2. email : professor@professor.com , password : professor
   3. email : student@student.com , password : student