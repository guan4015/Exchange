# Exchange
This project creates a baby version of stock exchange that simulates the trading between buy side and sell side. It consists of 20 classes,
and there are four classes supporting the structure of this project: Exchange, Market, OrderBook and PriceLevel.

## Function/Class Description

This project consists of twenty .java files. We split it into three parts.

### Main Functions

*  The Exchange.java encapsulates the tickers being traded, the resting orders in process as well as the communications between the   exchange and the clients. It can process the sweep order requested by the trader and cancel the resting order.
*  The Market.java encapsulates the bid book, offer book, the ticker being traded and the exchange information, which can be used to cancel and process the resting order. It equipped with method to process the sweeping order.
*  The OrderBook.java encapsulates the trading side, ticker information as well as the orders with prices described as a treemap. When a new order comes from the counter party, i.e. the bid book receives a selling order, then it will sweep the selling order by matching it with all information stored in the price treemap. It then processes the order accordingly.
*  The PriceLevel.java encapsulates all the orders corresponding to a certain price. It contains the sweep methods that receive the market and order information and matching them with all the orders at this price level. 

### Preparation Classes

*  The ClientId.java, ClientOrderId.java and MarketId contain the information about the client name, the order name and the ticker symbol, respectively.
*  The Price.java, Quantity.java specify the order price and quantity, equipped with comparable method.
*  The Side.java specifies the sides in the trading actions. It only contains two instances, Buy side or Sell side
*  The SweepingOrder.java and RestingOrder.java specify the order types. 

### Communication Classes
*  The Comms.java contains all the communications between clients and the exchange.
*  The Cancel.java, Cancellation.java and CancelRejected.java deal with the cancellation of the orders. The client intially sends a Cancel request. If it is sucessfully processed, then a Cancellation message is stored. If it is not successful, the rejection of the cancellation will be sent.
*  The Fill.java specify the information when a sweep order is being filled

## Implementation of the Exchange

The simulation of the exchange is summarized in the Test\_Exchange.java. We first create a buy side order. Since it cannot find the counterparty, it will then sit in the trading book. Once another sell side order has been created, it will automatically match with the one buy side order sitting in the trading book. Once they are matched, the filling messages will be sent to the exchange. Furthermore, if we would like to cancel the non-processing order, then we will send a Cancel request to the exchange and then we receive either a cancellation message or a rejection. Then the trading book will go back and forth by processing sell side orders and buy side orders.

## Authors

* **Xiao Guan** - *Initial work* - [Exchange](https://github.com/guan4015/Exchange)


## Acknowledgments

The author thanks Professor Eron Fishler for his help on this assignment.
