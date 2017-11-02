package ExchangeTerminal;
/**
 * This class defines the trading quantity correspond to certain price
 * @author xiaog
 *
 */
public class Quantity implements Comparable<Quantity> {
	
	protected long _quantity;
	
	public Quantity ( long quantity ) throws Exception {
		if( quantity < 0 ) {
			throw new Exception( "The quantity should be a number that is greater than or equals zero." );
		}
		_quantity = quantity;
	}
	
	public Quantity( final Quantity source ) {
		_quantity = source.getValue();
	}
	
	// Getters
	public long getValue() {
		return _quantity;
	}
	@Override
	public int hashCode() { 
		return Long.hashCode( _quantity ); 
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof Quantity ) )
			return false;
		return ((Quantity) object).getValue() == this.getValue();
	}
	
	@Override
	public int compareTo( Quantity quantity ) {
		long value_1 = this.getValue();
		long value_2 = quantity.getValue();
		if( value_1 > value_2 )
			return 1;
		if( value_2 > value_1 )
			return -1;
		return 0;
	}
	
	@Override
	public String toString() { 
		return String.format("Name: %s; Value: %d)", this.getClass().getName(), this.getValue() ); 
	}
	
	/**
	 * This function reduces the amount of buy/sell order when new transaction matches.
	 * @param quantityReduce
	 * @throws Exception
	 */
	public void reducingValueBy( Quantity quantityReduce ) throws Exception {
		if( this.compareTo( quantityReduce ) < 0 )
			throw new Exception( 
				String.format( 
					"Quantity reduction by %d overflow the current value %d.",  
					quantityReduce.getValue(), _quantity
				) 
			);
		_quantity -= quantityReduce.getValue();
	}

	

}
