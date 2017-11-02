package ExchangeTerminal;
/**
 * This class defines the rejection of cancellation when there is something wrong 
 * with this action
 * @author xiaog
 *
 */
public class CancelRejected extends SharedMessageField {
	
	public CancelRejected( ClientId clientId, ClientOrderId clientOrderId ) {
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
		if( !( object instanceof CancelRejected ) )
			return false;
		CancelRejected temp = (CancelRejected) object;
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
