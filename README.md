# ShoppingCart
microservices for a shopping cart

Information before you start:

- you'll need to use XAMP or WAMP to deploy your microservices, each one is configured with a different server port, at this way you can deploy each at same time.
- Once you've start XAMP or WAMP, you'll need to create a DataBase named "shoppingcart".
- If you already create the database, you can start all microservices.

# Swagger
To see the API's documentation we use swagger, you can access to it with the following URL: http://localhost:{serverPort}/swagger-ui/index.html
(in case you're deploying on a local server)

Microservices are distributed by the following server port configuration:
- Customer       port : 8080
- Orders         port : 8081
- OrderDetails   port : 8082
- Product        port : 8083
- Paymet         port : 8084
 

