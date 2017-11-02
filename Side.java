package ExchangeTerminal;

/**
 * 
 * @author xiaog
 *
 */
import java.util.Comparator;
/**
 * This class defines the trading sides in the trading business.
 * @author xiaog
 *
 */
public class Side {
	
	private String _side;
	private int _hashCode;
	private Comparator<Price> _comparator;


	private Side( String side, Comparator<Price> priceComparator ) {
		_side = side;
		_hashCode = _side.hashCode();
		_comparator = priceComparator;
	}
	
	@Override
	public String toString() {
		return String.format(
			"Name: %s; Attribute: %s",
				this.getClass().getName(),
				_side
		);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_comparator == null) ? 0 : _comparator.hashCode());
		result = prime * result + ((_side == null) ? 0 : _side.hashCode());
		return result;
	}
	
	@Override
	public boolean equals( Object object ) {
		return( this == object );
	}
	
	private static Comparator<Price> bidPriceComparator = new Comparator<Price>() {
		@Override
		public int compare( Price price_1, Price price_2 ) {
			return price_2.compareTo( price_1 );
		}
	};
	
	private static Comparator<Price> offerPriceComparator = new Comparator<Price>() {
		@Override
		public int compare( Price price_1, Price price_2 ) {
			return price_1.compareTo( price_2 );
		}
	};
	
    /**
     * The following function is used to inform which order we should use
     * @return the specific comparator used to insert the price
     */
	public Comparator<Price> getComparator() { return _comparator; }
	
	public static Side BUY = new Side( "BUY", bidPriceComparator );
	public static Side SELL = new Side( "SELL", offerPriceComparator );
	
}

