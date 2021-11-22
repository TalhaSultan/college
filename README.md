# College Management Systme
# Overview
This is the grails based application for college management system.
## Stack
- Grails: A powerful Groovy-based web application framework for the JVM built on top of Spring Boot
- JDK: 1.8
- grails: 3.2.6
- IDE: IntelliJ IDEA

## Getting Started
- Clone repository in to a directory
- Download grails 3.2.6 from https://grails.org/download.html
- After download set GRAILS_HOME to grails folder
- Add path variable in environment variables to grails/bin
- Check grails --version to confirm grails is setup
- Now got to college repository directory
- Run command (grails run-app)

## Brief
- Departments
    - College has many departments
    - CURD operations can be performed on http://localhost:8080/department/
    - you can get students in department using http://localhost:8080/department/students/{dept_id}
- Courses
    - Department offer many courses that student register/unregister
    - CURD operations can be performed on http://localhost:8080/course/
    - you can get students in course using http://localhost:8080/course/students/{course_id}
    - you can register for course using http://localhost:8080/course/register
    - you can un register for course using http://localhost:8080/course/unregister
- Student
    - as college has students so there are endpoint to manage that
    - CURD operations can be performed on http://localhost:8080/student/
- Database 
    - you can access database using http://localhost:8080/dbconsole 
    - use (jdbc:h2:mem:devDb) as JDBC URL
    - username (sa)
    - hit connect
    
## Brief Code Overview
- build.gradle contain the plugins
- grails-app folder contains the complete code
    - assets folder contains the images, javascript and stylesheets
    - conf folder hold the system configuration 
        1. application.yml hold configuration of database, hibernate, environments etc..
        2. logback hold system logging setting
    - controller contains all the controllers used within application
        - url mapping hold system mappings
    - domain hold all the data-model
    - init hold Bootstrap file that is used for init scripts and application startups
    - services contains all the services used within controllers
## Rest Endpoints
- adding postman collection in root directory with name (college.postman_collection.json)