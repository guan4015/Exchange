package ExchangeTerminal;
/**
 * This class defines the order that is actively traded.
 * @author xiaog
 *
 */
public class SweepingOrder {
	
	private ClientId _clientId;
	private Quantity _quantity;
	private Price _price;
	private ClientOrderId _clientOrderId;
	private MarketId _marketId;
	private Side _side;

	public SweepingOrder(
		ClientId clientId,
		ClientOrderId clientOrderId,
		MarketId marketId,
		Side side, 
		Quantity quantity,
		Price price
	) {
		_clientId = clientId;
		_quantity = new Quantity( quantity );
		_price = new Price( price );
		_clientOrderId = clientOrderId;
		_marketId = marketId;
		_side = side;
	}
	
	
	// Getter functions
	public ClientId getClientId() { 
		return _clientId; 
	}
	
	public ClientOrderId getClientOrderId() { 
		return _clientOrderId; 
	}
	
	public MarketId getMarketId() { 
		return _marketId; 
	}
	
	public Side getSide() { 
		return _side; 
	}

	public Quantity getQuantity() { 
		return _quantity; 
	} 
	
	public Price getPrice() { 
		return _price; 
	}


	public void reduceValueBy( Quantity quantityReduction ) throws Exception {
		if( quantityReduction.getValue() > this.getQuantity().getValue() )
			throw new Exception( String.format( "The quantity %d reduced overflows the current amount %d", 
					quantityReduction.getValue(), this.getQuantity().getValue() ) );
		_quantity.reducingValueBy( quantityReduction );
	}


	public boolean isFilled() { 
		return _quantity.getValue() == 0; 
	}
	
	@Override
	public String toString() {
		return String.format(
			"Name: %s; Client ID: %s; Client Order ID: %s;\n" +
			"Market ID: %s; Side: %s; Quantity: %s; Price: %s",
				this.getClass().getName(),
				this.getClientId(),
				this.getClientOrderId(),
				this.getMarketId(),
				this.getSide(),
				this.getQuantity(),
				this.getPrice()
		);
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof SweepingOrder ) )
			return false;
		SweepingOrder temp = (SweepingOrder) object;
		return(
			( this.getClientId().equals( temp.getClientId() ) ) &&
			( this.getClientOrderId().equals( temp.getClientOrderId() ) ) &&
			( this.getMarketId().equals( temp.getMarketId() ) ) &&
			( this.getSide().equals( temp.getSide() ) ) &&
			( this.getQuantity().equals( temp.getQuantity() ) ) &&
			( this.getPrice().equals( temp.getPrice() ) )
		);
	}
	
	@Override
	public int hashCode() { 
		return this.getClientOrderId().hashCode(); 
	}
}

