package ExchangeTerminal;
/**
 * This class defines the type of messages sent between the clients and the exchange,
 * shared by several classes: Cancel, Cancellation, CancelRejected 
 * and RestingOrderConfirmation.
 * @author xiaog
 *
 */
public abstract class SharedMessageField {
	
	protected ClientOrderId _clientOrderId;
	protected ClientId _clientId;
	
	public SharedMessageField( ClientId clientId, ClientOrderId clientOrderId ) {
		_clientId = clientId;
		_clientOrderId = clientOrderId;
	}
	
	public ClientId getClientId() { 
		return _clientId; 
	}
	public ClientOrderId getClientOrderId() { 
		return _clientOrderId; 
	}
	
	public abstract String getMessage();

}
