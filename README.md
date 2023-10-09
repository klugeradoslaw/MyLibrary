# MyLibrary app

## About the project

Aim of this application is minimizing the purchase of new books by borrowing them among friends/app users.
Instead of asking each friend if they have the book we are interested in - thanks to this app we can easily check for ourselves what titles are in their home library.

Selected functionalities:
* register new user, update user account, delete account
* creating libraries with your own books, 
* checking content of libraries  others users (list of purchased irl books),
* rating books.

## Built with
* Java
* Apache Maven
* MySQL
* Hibernate
* H2
* Spring Web
* Spring Data
* Docker
* Git

## Getting Started
App works on 34.118.29.234
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
| Method | Url            | Description                                                           | Example of valid Request Body     |
|--------|----------------|-----------------------------------------------------------------------|-----------------------------------|
| POST   | /signup        | Register new User.                                                                           |            |
| PATCH  | /user/{id}     | Update User data (account).                                                                  |            |
| DELETE | /user/{id}     | Delete user by ID.                                                                           |            |
| DELETE | /user?email=   | Delete user by email.                                                                        |            | 

### Book
| Method | Url            | Description                                                           | Example of valid Request Body     |
|--------|----------------|-----------------------------------------------------------------------|-----------------------------------|
| GET    | /book/{id}     | Find book by ID.                                                                             |            |
| GET    | /book          | Get list of all books.                                                                       |            |
| POST   | /book          | Add new book.                                                                                |            |
| PATCH  | /book/{id}     | Update book information with ID placed in variable path using information in request body.   |            | 
| DELETE | /book/{id}     | Delete book by ID.                                                                           |            | 

### Library
| Method | Url            | Description                                                           | Example of valid Request Body     |
|--------|----------------|-----------------------------------------------------------------------|-----------------------------------|
| GET    | /library/{id}  | Find library by ID.                                                                          |            |
| GET    | /library       | Get list of all libraries.                                                                   |            |
| GET    | /library?email=| Get list of all libraries by users's email (request param).                                  |            |
| GET    | /library/my    | Get list of yours libraries.                                                                 |            |
| POST   | /library       | Add new library.                                                                             |            |
| PATCH  | /library/{id}  | Update library information with ID placed in variable path using information in request body.|            | 
| PUT    | /library/{libraryId}/book/{bookId}| Add book to your library.                                                 |            |
| DELETE | /library/{id}  | Delete library by ID.                                                                        |            |  

### Others
| Method | Url            | Description                                                           | Example of valid Request Body     |
|--------|----------------|-----------------------------------------------------------------------|-----------------------------------|
| GET    | /genre         | Get list of all genres.                                                                      |            |
| POST   | /rating?bookId=X&rating=X| Add or update book rating (request params).                                        |            |
| GET    | /user/{id}     | Get user info by ID placed in path variable.                                                 |            |
| GET    | /user?email=   | Get user info by email as a request param.                                                   |            |

Examples of valid Request Bodies in progress...

## To do:

- [ ] Tests
- [ ] Friend list
- [ ] Functionality - library visible for everyone or only for friends
