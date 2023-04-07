# world-of-toys

This is a backend API for a toy store web application built using Spring 3.0.5 and Java 17. This API is responsible for handling all of the requests made to the server and returning the appropriate data to the front-end.

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Features](#features)
- [Installation and Usage](#installation-and-usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This web application is designed to allow users to view and purchase toys from a toy store. Users can browse through the available toys, add them to their cart, and checkout to purchase them.

## Technologies

This application was built using the following technologies:

- Spring 3.0.5
- Java 17
- Hibernate
- MySQL

## Features

The following features are currently available in the application:

- User registration and login

## Endpoints
The following endpoints are currently available in the API:

- POST api/v1/auth/register - User registration
- POST /api/v1/auth/authenticate - Login

## Installation and Usage

To run this application, please follow the steps below:

1. Clone the repository to your local machine
2. Import the project into your IDE
3. Set up a MySQL database and update the application.yml file with your database details
4. Run the application using the command `mvn spring-boot:run` or by running the main method in the `WorldoftoysApplication` class
5. Use a tool such as Postman to make requests to the API endpoints.

## Contributing

If you would like to contribute to this project, feel free to fork the repository and submit a pull request.

## License

This project is not licensed and is not intended for use or distribution.