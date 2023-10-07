# MyLibrary app

## About the project

Aim of this application is minimizing the purchase of new books by borrowing them among friends/app users.
Instead of asking each friend if they have the book we are interested in - thanks to this app we can easily check for ourselves what titles are in their home library.

Selected functionalities:
* register new user, update user account, delete account
* creating libraries with your own books, 
* checking content of libraries  others users (list of purchased books),
* rating books.

## Built with

* Java 17
* Apache Maven
* MySQL
* Hibernate
* H2
* Spring Web
* Spring Data
* Docker
* Git

## Getting Started

#### App works on 34.118.29.234
Feel free to check functionalities by f.e. Postman. Adding port isn't required thanks to nginx.

#### With Maven:

To get a local copy up and running follow these simple steps.

1. Clone the repository
   ```sh
   git clone https://github.com/klugeradoslaw/MyLibrary.git
   ```
2. Build project with maven
   ```sh
   mvn spring-boot:run
   ```

## Available endpoints

### Account

| Method | Url            | Description                                                                                        | Example of valid Request Body                                                                                                                                                                                         |
|--------|----------------|----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | /signup        | Register new User.  | |
| PATCH  | /user/{id}     | Update User data (account).|    |
| DELETE | /user/{id}     | Changing a student with an id placed in the path variable to a student placed in the request body. |            |
| DELETE | /user?email=   | Update student information with id placed in variable path using information in request body.      | 

.... IN PROGRESS ....

## To do:

- [ ] Friend list
- [ ] Functionality - library visible for everyone or only for friends
- [ ] Friend list
