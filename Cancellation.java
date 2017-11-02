package ExchangeTerminal;
/**
 * This class defines a supplement to Cancel message. It serves as the confirmation
 * of Cancel message.
 * @author xiaog
 *
 */
public class Cancellation extends SharedMessageField{
	
	public Cancellation( ClientId clientId, ClientOrderId clientOrderId ) {
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
		if( !( object instanceof Cancellation ) )
			return false;
		Cancellation temp = (Cancellation) object;
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
