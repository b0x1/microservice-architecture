#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Example", description = "An example model")
public class ExampleDto {
	private String someProperty;

	public String getSomeProperty() {
		return this.someProperty;
	}

	public void setSomeProperty(String someProperty) {
		this.someProperty = someProperty;
	}
}
