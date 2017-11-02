package ExchangeTerminal;
/**
 * This class defines the resting order that sits in the trading book
 * @author xiaog
 *
 */
public class RestingOrder {

	private SweepingOrder _sweepingOrder;
	private Quantity _quantity;

	public RestingOrder( SweepingOrder sweepingOrder ) {
		_sweepingOrder = sweepingOrder;
		_quantity = new Quantity( sweepingOrder.getQuantity() );
	}
	
	public SweepingOrder getSweepingOrder() { 
		return _sweepingOrder; 
	}
	
	public Quantity getQuantity() { 
		return _quantity; 
	}
	
	public ClientId getClientId() { 
		return _sweepingOrder.getClientId(); 
	}
	
	public ClientOrderId getClientOrderId() { 
		return _sweepingOrder.getClientOrderId(); 
	}
	
	public Price getPrice() { 
		return _sweepingOrder.getPrice(); 
	}
	/**
	 * The resting order is in order book and we allow the client to cancel it.
	 * @throws Exception
	 */
	public void cancel() throws Exception { 
		_quantity = new Quantity( 0L ); 
	}

	public void reduceValueBy( Quantity matchQuantity ) throws Exception {
		_quantity.reducingValueBy( matchQuantity );
	}

	public boolean isFilled() { 
		return _quantity.getValue() == 0; 
	}
	
	@Override
	public int hashCode() {
		return this.getClientOrderId().hashCode(); 
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof RestingOrder ) )
			return false;
		RestingOrder temp = (RestingOrder) object;
		return(
			( this.getQuantity().equals( temp.getQuantity() ) ) &&
			( this.getSweepingOrder().equals( temp.getSweepingOrder() ) )
		);
	}
	
	@Override
	public String toString() {
		return String.format(
			"Name: %s; Quantity: %s; \n"
				+ "%s",
				this.getClass().getName(),
				this.getQuantity().toString(),
				this.getSweepingOrder().toString()
		);
	}
	
}
