#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }

}
