package ExchangeTerminal;

/**
 * This class creates a link between the client and the exchange
 * @author xiaog
 *
 */

import java.util.LinkedList;

public class Comms {
	
	protected LinkedList<RestingOrderConfirmation> _restingOrders;
	protected LinkedList<Fill> _fills;
	protected LinkedList<CancelRejected> _cancelRejected;
	protected LinkedList<Cancellation> _cancelMessage;
	
	public Comms() {
		_restingOrders = new LinkedList<RestingOrderConfirmation>();
		_fills = new LinkedList<Fill>();
		_cancelRejected = new LinkedList<CancelRejected>();
		_cancelMessage = new LinkedList<Cancellation>();
		
	}
	
	public LinkedList<RestingOrderConfirmation> getRestingOrderConfirmations() { 
		return _restingOrders; 
	}
	
	public LinkedList<CancelRejected> getCancelRejections() { 
		return _cancelRejected; 
	}
	
	public LinkedList<Cancellation> getCancelationConfirmations() { 
		return _cancelMessage; 
	}
	
	// Fills getters and fills adders
	public LinkedList<Fill> getFills(){
		return _fills;
	}
	
	public void sendFill( Fill fill ) {
		_fills.addLast( fill );
	}
	
	public void sendRestingOrderConfirmation ( RestingOrderConfirmation restingOrderConfirmation) {
		_restingOrders.addLast( restingOrderConfirmation );
	}
	
	public void sendCancelRejected( CancelRejected rejectMsg ) {
		_cancelRejected.addLast( rejectMsg );
	}
	
	public void cancellation( Cancellation cancelMessage ) {
		_cancelMessage.addLast( cancelMessage  );
	}

}
