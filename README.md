# Loan Amortization Schedule Generator

This is a basic loan amortization schedule generator which runs as a Restful API.

There is only one endpoint in this application at the moment as below:

  - **POST:** http://localhost:8080/api/loanAmortization/generate

  - **Request Body:**
       ```
       {
          "loanAmount" : 120000,
          "years" : 30,
          "interestRate" : 10
       }
       ```
  - **Response** (partial response of "schedule" is displayed below):
  ```
      {
        "monthlyPayment": 1053.0858841065592,
        "totalPayment": 379110.9182783613,
        "schedule": [
          {
              "paymentNumber": 1,
              "interest": 1000.0,
              "principal": 53.085884106559206,
              "balance": 119946.91411589344
          },
          {
              "paymentNumber": 2,
              "interest": 999.5576176324453,
              "principal": 53.528266474113934,
              "balance": 119893.38584941933
          }
        ]
      }
```
