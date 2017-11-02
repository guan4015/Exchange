package ExchangeTerminal;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * This class defines the price level, which consists of a price and a linked list
 * made up of resting order. 
 * @author xiaog
 *
 */
public class PriceLevel {
	
	private Price _price;
	private LinkedList<RestingOrder> _restingorders; // The price level consisting of resting order
	
	public PriceLevel( Price price ) {
		_price = new Price( price );
		_restingorders = new LinkedList<RestingOrder>();
	}
	
	public Price getPrice() {
		return _price;
	}
	
	public LinkedList<RestingOrder> getOrders() { 
		return _restingorders; 
	}
	// The following method add the resting order to the price level.
	public void makeRestingOrder( RestingOrder restingOrder ) {
		_restingorders.addLast( restingOrder );
	}
	
	public boolean sweep( Market market, SweepingOrder sweepOrder ) throws Exception {
		// Generate the iterator from the beginning to the terminal
		Iterator<RestingOrder> restingOrdersIterator = _restingorders.iterator();
		// Traverse the linked list
		while( restingOrdersIterator.hasNext() ) {
			RestingOrder restingOrder = restingOrdersIterator.next();
			// Judge if the quantity of the resting order is zero
			if( restingOrder.isFilled() ) {
				restingOrdersIterator.remove();
			} else {
				// We attempt to match them
				long sweepNum = sweepOrder.getQuantity().getValue();
				long restingNum = restingOrder.getQuantity().getValue();
				long matchNum = Math.min( sweepNum, restingNum );
				// Send the fill to the exchange
				market.getExchange().getComms().sendFill( 
					new Fill( 
						restingOrder.getClientId(), // Counter party id
						sweepOrder.getClientId(), // Client id
						restingOrder.getClientOrderId(), // Counter party order id
						new Quantity( matchNum ) 
						) 
				);
				// Send the fill to exchange
				market.getExchange().getComms().sendFill(
					new Fill( 
					    sweepOrder.getClientId(), // Client id
					    restingOrder.getClientId(), // Counter party id
						sweepOrder.getClientOrderId(), // Client order id
						new Quantity( matchNum ) 
						) 
				);
				// Reduce the order quantity by matched quantity.
				sweepOrder.reduceValueBy( new Quantity( matchNum ) );
				restingOrder.reduceValueBy( new Quantity( matchNum ) );
				
				// Did we completely fill the resting order?
				if( restingOrder.isFilled() ) {
                    // If the restingOrder has been filled, then
					// we remove this order from the exchange
					market.getExchange().removeOrder( restingOrder );
					// remove this resting order from the price level
					restingOrdersIterator.remove();
					if( sweepOrder.isFilled() )
                        // if the sweep order has also been filled
						// if the resting order has successor, we preserve this level
						return !restingOrdersIterator.hasNext();
				} else {
                    // In this case, the sweep order has been filled but there
					// are some resting orders in this level.
					return false;
				}

			}
		}
        // If we will run the following line, this means that this Price Level has been
		// wiped out by the sweep order.
		return true; 
	}
	
}
