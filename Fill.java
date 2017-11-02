package ExchangeTerminal;
/**
 * This class defines the Fill message that will be called when we sweep the order.
 * @author xiaog
 *
 */
public class Fill extends SharedMessageField{
	
	protected ClientId _counterpartyId;
	protected Quantity _quantity;
	
	public Fill( ClientId clientId, ClientId counterpartyId, ClientOrderId clientOrderId, Quantity fillQuantity ) {
		super( clientId, clientOrderId );
		_counterpartyId = counterpartyId;
		_quantity = fillQuantity;
	}
	
	@Override
	public String getMessage() { 
		return this.toString(); 
	}
	
	@Override
	public String toString() { 
		return String.format( 
			"Name: %s; Client ID: %s; Counter Party ID: %s; Client Order ID: %s; Quantity: %s\n", 
				Fill.class.getName(), 
				this.getClientId().toString(), 
				this.getCounterpartyId().toString(), 
				this.getClientOrderId().toString(), 
				this.getQuantity().toString() 
		);
	}
	
	public ClientId getCounterpartyId() { 
		return _counterpartyId; 
	}
	public Quantity getQuantity() { 
		return _quantity; 
	}
	
	@Override
	public boolean equals( Object object ) {
		if( this == object )
			return true;
		if( !( object instanceof Fill ) )
			return false;
		Fill temp = (Fill) object;
		return(
			( this.getClientId().equals( temp.getClientId() ) ) &&
			( this.getCounterpartyId().equals( temp.getCounterpartyId() ) ) &&
			( this.getClientOrderId().equals( temp.getClientOrderId() ) ) &&
			( this.getQuantity().equals( temp.getQuantity() ) )
		);
	}
	
	public int hashCode() {
		return this.getClientOrderId().hashCode();
	}

	

}
