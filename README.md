# Customer Management Application

This application allows users to manage customers, including adding, editing, deleting, and searching for customer records. The application includes a login system and utilizes JWT tokens for authentication.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Frontend Functionality](#frontend-functionality)
- [Images](#images)

## Features

- User authentication with JWT tokens
- Add, edit, delete customer records
- Search customer records
- Sync customer records with backend
- Responsive design

## Technologies Used

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Spring Boot, Java
- **Database**: MySQL 
- **Authentication**: JWT (JSON Web Tokens)

## Installation

### Prerequisites

- Java 8 or above
- Maven
- Spring

### Backend Setup

1. Clone the repository:

    ```sh
    git clone https://github.com/Kapil7982/sunbase.git
    ```

2. Navigate to the backend directory:

    ```sh
    cd SpringSecurityWithJwt
    ```

3. Build the project with Maven:

    ```sh
    mvn clean install
    ```

4. Run the application:

    ```sh
    mvn spring-boot:run
    ```

### Frontend 

1. HTML

2. CSS

3. JavaScript

## Usage

### Login

1. Open the application in your web browser.
2. Navigate to the login page.
3. Enter your login credentials.
4. Upon successful login, you will be redirected to the customer list page.

### Managing Customers

- **Add Customer**: Click the "Add Customer" button and fill out the form.
- **Edit Customer**: Click the edit button next to the customer you want to edit.
- **Delete Customer**: Click the delete button next to the customer you want to delete.
- **Search Customers**: Use the search bar to filter customers by keyword.

## API Endpoints

### Authentication

- `GET /signIn` - Login endpoint (Basic Auth)

### Customers

- `GET /customers` - Get all customers 
- `POST /customers` - Add a new customer
- `GET /customers/search` - Search customers by keyword
- `DELETE /{customerId}` - Delete a customer by ID

### Sync

- `POST /fetch-and-save-customers` - Sync customers with the backend

## Frontend Functionality

### Event Listeners

- **Login Form**: Handles user login and stores JWT token.
- **Add Customer Form**: Submits new customer data to the backend.
- **Sync Button**: Syncs customer data with the backend.
- **Search Input**: Filters customer list based on search keyword.

### Functions

- **loadCustomers**: Fetches and displays customer data.
- **displayCustomers**: Renders customer data in the table.
- **editCustomer**: Redirects to the edit customer page.
- **deleteCustomer**: Deletes a customer and reloads the customer list.
- **logout**: Clears the JWT token and redirects to the login page.

## Images

![Screenshot 2024-07-27 072359](https://github.com/user-attachments/assets/9f5270c1-2885-47a1-9351-024b4393edc3)
-
![Screenshot 2024-07-27 072316](https://github.com/user-attachments/assets/c1188873-833b-4c54-8d15-b0f326a0a590)
-
![Screenshot 2024-07-27 072250](https://github.com/user-attachments/assets/ccb1fc8a-1d15-43ee-a412-2f572ccdd2a0)
-
![Screenshot 2024-07-27 072208](https://github.com/user-attachments/assets/d1e1e8eb-88be-42ce-b954-7d6ac35e45bf)




