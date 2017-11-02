package ExchangeTerminal;
/**
 * This class defines the Market ID. 
 * @author xiaog
 *
 */
public class MarketId extends AbstractNonemptyStringHolder{
	
	
	public MarketId(String source) throws Exception {
		super(source);
	}
	
	@Override
	public String getFieldName() {
		return "market ID";
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + 
		       "(" + super.getValue().toString() + ")";
	}
	
	@Override
	public boolean equals( Object object ) {
		if( !( object instanceof MarketId ) )
			return false;
		if( object == this )
			return true;
		MarketId temp = (MarketId) object;
		return this.getValue().equals( temp.getValue() );
	}
	
	@Override
	public int hashCode() { 
		return this.getValue().hashCode(); 
	}
	

}
