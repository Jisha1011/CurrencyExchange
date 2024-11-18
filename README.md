# CurrencyExchange
CurrencyExchange Demo Application for converting bills to target currency from original currency

***********************************************************
Endpoint to obtain exchange rate:

ExhangeRateAPI-https://app.exchangerate-api.com/\
***********************************************************
Run the application:

mvn spring-boot:run
***********************************************************
Hit the API http://localhost:8080/app/calculate from Postman

API Request Method: POST

API Request Body: Type- JSON
{
   "item":[{ "item":"Bean",
    "itemCategory": "grocery",
    "amount": 105},
    {"item":"Mobile",
    "itemCategory":"electronics",
    "amount":"20"}],
    "userType":"affiliate",
    "customerTenure":3,
    "originalCurrency":"INR",
    "targetCurrency":"INR"
}
***********************************************************