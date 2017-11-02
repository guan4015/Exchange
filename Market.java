package ExchangeTerminal;
/**
 * This class defines the Market class that stores the exchange, market ID, 
 * bidbook and offerbook. It also contains the function that sweep the order.
 * @author xiaog
 *
 */
public class Market {
	
	protected Exchange _exchange;
	protected MarketId _marketId;
	protected OrderBook _bidBook;
	protected OrderBook _offerBook;
	
	// Constructor
	public Market () {}
	public Market( Exchange exchange, MarketId marketId ) {
		_exchange = exchange;
		_marketId = marketId;
		_bidBook = new OrderBook( this, Side.BUY );
		_offerBook = new OrderBook( this, Side.SELL );
		_bidBook.setCounterSide( _offerBook );
		_offerBook.setCounterSide( _bidBook );
		
	}
	
	//Getters
	public Exchange getExchange() {
		return _exchange;
	}
	
	public MarketId getMarketId() {
		return _marketId;
	}
	
	public OrderBook getBidBook() {
		return _bidBook;
	}
	
	public OrderBook getOfferBook() {
		return _offerBook;
	}
	
	// Modifiers
	public void setExchange( Exchange exchange ) {
		_exchange = exchange;
	}
	
	public void setMarketId( MarketId marketId) {
		_marketId = marketId;
	}
	
	public void sweep( SweepingOrder sweepingOrder ) throws Exception {
		if( sweepingOrder.getSide() == Side.SELL )
			_bidBook.sweep( sweepingOrder );
		else
			_offerBook.sweep( sweepingOrder );
	}
	

}
