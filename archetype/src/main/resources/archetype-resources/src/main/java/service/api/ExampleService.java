#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.api;

import ${package}.data.model.Example;

public interface ExampleService {
    Example getExample(Long id);
    Example createExample(Example example);
    String getException();
}
