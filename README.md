The project was developed under the IDE of Inteliij with Java 17, based on muserver.io. 
There are 3 APIs developed. the endpoints are
1. Request a new booking at restaurant
    http://localhost:8080/bookings  POST request
the sample JSON body request :
{
    "customerPhone":"4163012362",
    "cusomerFirstName":"Victor",
    "cusomerLastName":"Wang",
    "tableSize":"SMALL",
    "bookedDateTime":"2025-06-09 16:30:02",
    "restaurantName" :"Barbeque Grill"
}

2. lookup the bookings by customer Phone#:
    http://localhost:8080/bookings/4163012361   GET Request


3. lookup the bookings by the specific date :
   http://localhost:8080/bookings/queryByDate?bookedDate=2025-06-09 GET request,
   with which the Restaurant owner can lookup the bookings by the date


   the System persists the bookings in the cache memory.

   The APIs are documented by swagger, to have access to swagger for the APIs,
   go to the URL https://petstore.swagger.io/ ,
   and explore the URL http://localhost:8080/openapi.json, asuming you run the application on your localhost.

   To test the APIs, just start the application with the command:

   $start.sh
   
   

