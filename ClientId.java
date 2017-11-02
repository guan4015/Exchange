package ExchangeTerminal;
/**
 * This class defines the ClientId.
 * @author xiaog
 *
 */

public class ClientId extends AbstractNonemptyStringHolder{
	
	public ClientId(String clientId) throws Exception {
		super(clientId);
	}
	
	@Override
	public String getFieldName() {
		return "client ID";
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + 
		       "(" + super.getValue().toString() + ")";
	}
	
	@Override
	public boolean equals( Object object ) {
		if( !( object instanceof ClientId ) )
			return false;
		if( object == this )
			return true;
		ClientId temp = (ClientId) object;
		return this.getValue().equals( temp.getValue() );
	}
	
	@Override
	public int hashCode() { 
		return this.getValue().hashCode(); 
	}

}
