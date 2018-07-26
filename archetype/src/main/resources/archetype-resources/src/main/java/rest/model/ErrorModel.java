#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.Optional;

@ApiModel(value = "error", description = "The model representing an REST error response")
public class ErrorModel {

    private final RuntimeException exception;

    public ErrorModel(RuntimeException exception) {
        this.exception = exception;
    }

    @JsonProperty("Exception")
    public String getException() {
        return this.exception.getClass().getCanonicalName();
    }

    @JsonProperty("Message")
    public String getMessage() {
        return this.exception.getMessage();
    }
}
