package ExchangeTerminal;
/**
 * This class defines the Cancel class that will be sent when we
 *  actively would like to cancel the order.
 * @author xiaog
 *
 */
public class Cancel extends SharedMessageField{
	
	public Cancel( ClientId clientId, ClientOrderId clientOrderId ) {
		super( clientId, clientOrderId );
	}

	@Override
	public String getMessage() { 
		return this.toString(); 
	}
	
	@Override
	public String toString() {
		return String.format( 
			"Name: %s; Client ID: %s; Client Order ID: %s\n", 
				this.getClass().getName(), 
				this.getClientId().toString(), 
				this.getClientOrderId().toString()
		);
	}
	

	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof Cancel ) )
			return false;
		Cancel temp = (Cancel) object;
		return( 
			this.getClientId().equals( temp.getClientId() ) && 
			this.getClientOrderId().equals( temp.getClientOrderId() )
		);
	}
	
	@Override
	public int hashCode() { 
		return this.getClientOrderId().hashCode(); 
	}

}
