
# The Stock Watchlist API

This is a rather simple demonstration of an API implemented with Spring boot. This API features the ability to 

## Starting Up

To the the App running simply clone this repository and run the following commands:

    mvn compile
    mvn spring-boot:run

## API endpoints

To be able to list all stocks run the following:

    curl -u apiuser:passtoken http://localhost:8080/stocks

To list one stock with the ID `1` use:

    curl -u apiuser:passtoken http://localhost:8080/stocks/1

To get the current performance about a particular stock e.g AAPL run

    curl -u apiuser:passtoken http://localhost:8080/api/quotes?symbol=AAPL

## User Interface

There is also a User Interface that can be accessed at `http://localhost:8080/ui`. The account for the Web UI has username `user` and password `password`. Feel free to explore it!


