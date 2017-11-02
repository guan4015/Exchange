package ExchangeTerminal;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class defines the Exchange that is used to store and process the tickers 
 * being traded, resting orders and communications between clients and the exchange. 
 * 
 * @author xiaog
 *
 */
public class Exchange {
	
	// MarketId is the ticker symbol
	protected HashMap<MarketId, Market> _record;
	protected HashMap<ClientOrderId, RestingOrder> _processingOrders;
	protected Comms _comms;
	
	// Default constructor
	public Exchange () {
		_record = new HashMap<MarketId, Market>();
		_processingOrders = new HashMap<ClientOrderId, RestingOrder>();
		_comms = new Comms();
		
	}
	
	public void addMarket(Market market) {
		_record.put(market.getMarketId(), market);
		
	}
	
	public Market getMarket(MarketId marketid) {
		return _record.get(marketid);
		
	}
	
	// The following method is the one that make the user cancel his/her orders
	public void cancel( Cancel cancelMessage ) throws Exception {
		RestingOrder restingOrder = _processingOrders.get( cancelMessage.getClientOrderId() );
		if( restingOrder == null ) {
			_comms.sendCancelRejected( new CancelRejected( cancelMessage.getClientId(), cancelMessage.getClientOrderId() ) );
		} else {
			restingOrder.cancel();
	        _processingOrders.remove( cancelMessage.getClientOrderId() );
			_comms.cancellation( new Cancellation( cancelMessage.getClientId(), cancelMessage.getClientOrderId() ) );
		}
	}
	// Adding the resting order
	public void AddingRestingOrder( RestingOrder restingOrder ) {
		_processingOrders.put( restingOrder.getClientOrderId(), restingOrder );
		// Send client a message about this order
		RestingOrderConfirmation restingOrderConfirmation = new RestingOrderConfirmation( restingOrder );
		_comms.sendRestingOrderConfirmation( restingOrderConfirmation );
	}
	
	public void sweep( SweepingOrder sweepingOrder ) throws Exception {
		// Retrieve the market
		Market market = _record.get( sweepingOrder.getMarketId() );
		// Once the sweep has been done. We have to manipulate the order book
		// If the order could be filled, then the price levels in the bid book and offer book should 
		// match each other.
		market.sweep( sweepingOrder );
		// The following should retrieve the resting order to the resting orders
	}
	
    // Getters
	public RestingOrder getOrder( ClientOrderId clientorderId ) {
		return _processingOrders.get( clientorderId );
	}
	
	public HashMap<ClientOrderId, RestingOrder> getProcessingOrder() {
		return _processingOrders;
	}
	
	public Comms getComms() {
		return _comms;		
	}
	
	// Modifiers
	public void removeOrder( RestingOrder restingorder ) {
		_processingOrders.remove( restingorder.getClientOrderId() );
	}
	

	
	

}
