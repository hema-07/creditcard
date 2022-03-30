## Credit Card Application

##### Language  :  Java (Spring boot) 2.5.0
##### Database  :  H2 
##### Test      :  Unit Test
##### CI/CD     :  Jenkins

### Functionality


This is a Spring boot application which demonstrates adding new card and get All the cards as list from DB.

I have used H2 database in this application. It accepts Credit card modal as a request. 

    Spring security added to these endpoints. It checkes the encoded username and passcode that had already assinged in config file.
    
    When Credit card request enters the endpoint, it passes to Validator method to validate the request.

    After validation, validated request enters into credit card valid check (LUHN Algorithm).

    Once i received a valid Credit card number, i am checking with DB whether credit card is exist or new one.

    After checking, Valid credit card is saved to H2 DB and Invalid card will throw an error response.

I have written test cases for success and failure scenario.

I have installed my own jenkins and created a simple pipeline that pushes my code to pipeline and build it.

### Run configuration

    Run this credit card application as spring boot application.
    Test file run configuration setup screenshot is in docs folder. 

### REST POST endpoint for adding new credit card

This following values are hitting the endpoint as a request from user.

    CREDIT_ACCOUNT_ID - creating unique id for credit details

    CREDIT_CARD_NUMBER - from request body

    CREDIT_CARD_HOLDER_NAME - from request body

    CREDIT_LIMIT - from request body

    CREDIT_BALANCE - for new card, by default 0


    Post call : http://localhost:8082/add

    {
            "CardNumber":"61789372994",
            "Name":"Andrew",
            "Limit":"800.00"
    }
    
    ---------------------------------------
    
##### Headers: 
    
    Content-Type:application/json
    
    Accept:application/json
    
It return status code for success scenario.

For failure, i have implemented error code and error handling.

#### REST GET endpoint for fetch all the credit card values
    
This endpoint doesn't have body, it directly fetches all credit card data as a list from DB.

    GET call : http://localhost:8082/getAll  
    
##### Headers: 
    
    Content-Type:application/json
    
    Accept:application/json
    
It return status code and list of Credit card for success scenario.

For failure, i have implemented error code and error handling.

### Preparation 

Skeleton Project from  https://start.spring.io  

Jenkins: http://localhost:8080

Attached screenshot of my local Jenkins CI/CD page is in docs folder.

### Reference LUHN Algorithm 

https://en.wikipedia.org/wiki/Luhn_algorithm

https://en.wikipedia.org/wiki/Luhn_algorithm#Example_for_validating_check_digit


### Sonar Qube

    mvn clean verify sonar:sonar \
      -Dsonar.projectKey=CreditCard \
      -Dsonar.host.url=http://localhost:9000 \
      -Dsonar.login=*loginID*