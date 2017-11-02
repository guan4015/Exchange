package ExchangeTerminal;
/**
 * This class defines the price represented in the exchange
 * @author xiaog
 *
 */
public class Price implements Comparable<Price> {
	// We scale the price to avoid round-off by ten thousand
	protected long _pricesScaling;  
	
	
	public Price( long pricesScaling ) throws Exception {
		if( pricesScaling <= 0 ) {
			throw new Exception( "The input of the price should be greater than zero." );
		}
		_pricesScaling = pricesScaling;
	}
	
	// Copy constructor
	public Price( Price source ) {
		_pricesScaling = source.getValue();
	}
	
	// Getters
	public long getValue() {
		return _pricesScaling;
	}
	
	@Override
	public int hashCode() { 
		return Long.hashCode( _pricesScaling ); 
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof Price ) )
			return false;
		Price temp = (Price) object;
		return this.compareTo( temp ) == 0;
	}
	
	@Override
	public int compareTo( Price priceComp ) {
		long value_1 = this.getValue();
		long value_2 = priceComp.getValue();
		if( value_1 > value_2 )
			return 1;
		if( value_2 > value_1 )
			return -1;
		return 0;
	}
	
	@Override
	public String toString() { 
		return String.format( "Name: %s; Value: %d", this.getClass().getName(), this.getValue() ); 
	}
	
	


}
