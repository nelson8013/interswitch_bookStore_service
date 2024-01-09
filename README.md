# Online Book Store

![image](https://media.istockphoto.com/id/854284330/photo/online-library.jpg?s=2048x2048&w=is&k=20&c=uJEe62SvCrAU07QSEbE5hwnzSR2cPxRkBcBSsT4BxSs=)

## PREREQUISITES

### Database: Mysql
Details for configuring your database.

- You'll also need to have MySQL installed locally on your machine.
- Running on ```port: 3306```,
- without a password ```password:   ```
- with a ```user/username: root```
- Then create a new database called ``` bookstore ```.


## Build Instructions
1. Clone the repository.
2. Navigate to the project directory [Bookstore-service].
   ```bash 
    ./mvnw clean install
    ```

## Run Instructions
Before running the application, navigate to the application.properties file located at ``` Bookstore-service\src\main\resources ``` 
and update the fields below if you have a user/username and user name different from the suggested credentials above.

```bash
   spring.datasource.username=your_username
    spring.datasource.password=your_password
```

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
