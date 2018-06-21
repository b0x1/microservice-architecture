#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.api;

import ${package}.service.model.Example;

public interface ExampleService {
    Boolean createExample(Example example);
}
