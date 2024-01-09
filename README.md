# Online Book Store

![image](https://media.istockphoto.com/id/854284330/photo/online-library.jpg?s=2048x2048&w=is&k=20&c=uJEe62SvCrAU07QSEbE5hwnzSR2cPxRkBcBSsT4BxSs=)

## PREREQUISITES

### Database: Mysql
Details for configuring your database.

- You'll also need to have MySQL installed locally on your machine.
- Running on ```port: 3306```,
- without a password ```password:   ```
- with a ```user/username: root```
- The application is to be run locally.


## Build Instructions
1. Clone the repository.
2. Navigate to the project directory [DroneService].
   ```bash 
    ./mvnw clean install
    ```

## Run Instructions
1. Run the following command to start the application:
    ```bash 
    ./mvnw spring-boot:run
    ```

## Test Instructions
- Run the following command to execute JUnit tests:
    ```bash
    ./mvnw test
    ```

## API Endpoints
- Use Postman or any other API client to access the endpoints
- BASE URL: `http://127.0.0.1:5634/api/v1`
