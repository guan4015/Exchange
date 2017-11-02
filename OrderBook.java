package ExchangeTerminal;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class OrderBook {
	
    protected TreeMap<Price,PriceLevel> _priceLevels;
	protected Market _market;
	protected Side _side;
	protected OrderBook _counterSide;
	
	public OrderBook( Market market, Side side ) {
		_market = market;
		_side = side;
		// The price levels should be stored based on the side.
		// For BUYSIDE, the price levels should decrease 
		// For SELLSIDE, the price levels should increase
		_priceLevels = new TreeMap<Price,PriceLevel>( side.getComparator() );
	}
	
	public Side getSide() {
		return _side;
	}
	
	public OrderBook getCounterSide() {
		return _counterSide;
	}
	
	protected void setCounterSide( OrderBook orderBook ) { 
		_counterSide = orderBook; 
	}
	
	public void generateRestingOrder( SweepingOrder sweeporder ) throws Exception {
		RestingOrder restingorder = new RestingOrder( sweeporder );
		// Get price level
		PriceLevel priceLevel = _priceLevels.get( sweeporder.getPrice() );
		// Judge if the price level is in the order book
		if ( priceLevel == null ) {
			// If null, we generate price level and add this order
			priceLevel = new PriceLevel( sweeporder.getPrice() );
			priceLevel.makeRestingOrder( restingorder );
			_priceLevels.put( sweeporder.getPrice(), priceLevel );
		} else {
			// If not null, we directly add this order to the existing level.
			priceLevel.makeRestingOrder( restingorder );
		}
		// Last but not least, we should add this order to the exchange.
		_market.getExchange().AddingRestingOrder( restingorder );
		
	}
	/**
	 * The following method return the levels of the price of this book,
	 * where each price level is a linked list.
	 * @return
	 */
	public TreeMap<Price,PriceLevel> getPriceLevels() {
		return _priceLevels;
	}
	/**
	 * This method is used to sweep the order with counter party. 
	 * @param sweeporder
	 * @throws Exception
	 */
	public void sweep( SweepingOrder sweeporder ) throws Exception {
		// Judge if there is something wrong, in particular if the quantity is zero
		if( ( sweeporder == null ) || ( sweeporder.isFilled() ) )
			throw new Exception( "Invalid Order. Please check the order quantity and other attributes" );
		
		// Suppose the current book is a bid book and the sweep order is a sell order,
		// then we should traverse the bid book by the price key.
		// If the book is not empty, we can go forward.
		if( !_priceLevels.isEmpty() ) {
			// Define the iterator of the tree map
			Iterator<Entry<Price,PriceLevel>> priceLevelIterator = _priceLevels.entrySet().iterator();
			
			while ( priceLevelIterator.hasNext() ) {
	            // Retrieve the elements
		        Entry<Price,PriceLevel> entry = priceLevelIterator.next();
		        // Retrieve the price level
		        PriceLevel priceLevel = entry.getValue();
				// Suppose the current order book is bid book and sweep order is SELL,
				// if the price of the sweep order is greater than the price of the price level, we break the loop
				// Initially, if one of them passes, we are going to consume this sweep order
				if( this.getSide().getComparator().compare( sweeporder.getPrice(), priceLevel.getPrice() ) == -1 )
					break;
				// We then gradually digests this order and returns true/false to 
				// judge if it is fully digested.
				boolean removePriceLevel = priceLevel.sweep( _market, sweeporder );
				// We judge if the price Levels of the order are perfectly matched 
				if ( removePriceLevel )
					priceLevelIterator.remove();
			}	
		}
		
		// If this order has partially filled, we generateRestingOrder on the CounterSide
		if( !sweeporder.isFilled() )
			this.getCounterSide().generateRestingOrder( sweeporder );
	}

}
