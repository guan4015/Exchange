package ExchangeTerminal;
/**
 * This abstract class defines the common things shared by several classes: 
 * ClientId, ClientOrderId, MarketId. It makes sure that constructor cannot
 * have empty string field.
 * @author xiaog
 *
 */
public abstract class AbstractNonemptyStringHolder {
	
	protected static String ERROR_message = "The field %s should contain a non empty String";
	protected String _string;
	
	public AbstractNonemptyStringHolder( String source ) throws Exception {
		if( source == null )
			throw new Exception( String.format( ERROR_message, getFieldName() ) );
		if( source.equals( "" ) )
			throw new Exception( String.format( ERROR_message, getFieldName() ) );
		_string = source;
	}
	
	
	public abstract String getFieldName(); 
	
	public String getValue() { 
		return _string; 
	}

}
