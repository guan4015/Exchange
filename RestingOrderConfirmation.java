package ExchangeTerminal;
/**
 * This class defines the resting order confirmation that is generated when a new
 * resting order comming out.
 * @author xiaog
 *
 */
public class RestingOrderConfirmation extends SharedMessageField{
	
    protected RestingOrder _restingOrder;
    
	public RestingOrderConfirmation( RestingOrder restingOrder ) {
		super( restingOrder.getClientId(), restingOrder.getClientOrderId() );
		_restingOrder = restingOrder;
	}

	public RestingOrder getRestingOrder() { 
		return _restingOrder; 
	}

	@Override
	public String getMessage() { 
		return this.toString(); 
	}
	
	@Override
	public String toString() {
		return String.format(
			"Name: %s; Resting Order: %s",
				this.getClass().getName(),
				this.getRestingOrder().toString()
		);
	}
	
	@Override
	public int hashCode() { 
		return _restingOrder.hashCode(); 
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof RestingOrderConfirmation ) )
			return false;
		RestingOrderConfirmation temp = (RestingOrderConfirmation) object;
		return(
			( this.getRestingOrder().equals( temp.getRestingOrder() ) ) &&
			( this.getClientId().equals( temp.getClientId() ) ) &&
			( this.getClientOrderId().equals( temp.getClientOrderId() ) )
		);
	}

}
