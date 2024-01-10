# Online Book Store

![image](https://i.pinimg.com/564x/a9/cb/7d/a9cb7d045cdf52ccda8bc00851ac1a70.jpg)

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
Before running the application, if you have a user/username and username different from the suggested credentials above,
navigate to the application.properties file located at ``` Bookstore-service\src\main\resources ``` and update the fields below.

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

### CART
Add Book To Cart

- **Endpoint:** `POST /cart/add-to-cart`
- **Request Body:**
  - `addToCartRequest` - pictorial detail of the cart request.
    
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/75df85ac-54dc-450f-a772-398147411652)


- **Response:**
  - Status: 201 Created
  - Body: Create cart response.
   - `addToCartResponse` - pictorial detail of the cart response.
     
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/47011af6-7719-4e24-97d8-d8bbecb3370e)

View Cart

- **Endpoint:** `GET /cart/viewCart/userId?userId=2`
  - `viewCart` - pictorial detail of the cart.
 
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/be14f826-0fb0-4955-9663-6d9ab4c3ca45)
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/58440911-0808-4b9a-b6e4-8900112f8f69)

    


Checkout

- **Endpoint:** `POST /checkout`
- **Request Body:**
  - `checkoutRequest` - pictorial detail of the checkout request.
    
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/60db17a7-47dc-43c8-ad19-5caf763fdd63)


- **Response:**
  - Status: 201 Created
  - Body: Checkout response.
   - `addToCartResponse` - pictorial detail of the checkout response.
     
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/9dbab297-794e-4d0a-a695-823439f50842)
    ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/cc30c5f6-6e99-40d7-91db-95e49fbdb131)


Purchase History

- **Endpoint:** `GET /purchase-history/2`
  - `viewCart` - pictorial detail of the purchase history.

  ![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/3e6dca3f-e0c1-4970-845c-4c3a3590184c)


Search book by title

- **Endpoint:** `GET /books/title?title=The%20Great%20Gatsby`
  - `viewCart` - pictorial detail of the a search for a book by title: Great Gatsby.

![image](https://github.com/nelson8013/interswitch_bookStore_service/assets/12644704/af540f97-efd4-42a5-9976-17de3f07cb3e)





