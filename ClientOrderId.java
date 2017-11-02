package ExchangeTerminal;

public class ClientOrderId extends AbstractNonemptyStringHolder {
	
	public ClientOrderId(String clientOrderId) throws Exception {
		super(clientOrderId);
	}
	
	@Override
	public String getFieldName() {
		return "Client Order ID";
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + 
		       "(" + super.getValue().toString() + ")";
	}
	
	@Override
	public boolean equals( Object object ) {
		if( !( object instanceof ClientOrderId ) )
			return false;
		if( object == this )
			return true;
		ClientOrderId temp = (ClientOrderId) object;
		return this.getValue().equals( temp.getValue() );
	}
	
	@Override
	public int hashCode() { 
		return this.getValue().hashCode(); 
	}

}
