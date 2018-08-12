#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration.converter;

import ${package}.service.model.Example;
import org.eclipse.microprofile.config.spi.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleConverter implements Converter<Example> {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public Example convert(String val) {
        Example result = new Example();
        result.setSomeProperty(val);

        LOG.debug('"' + val + "\" converted to " + result.toString());

        return result;
    }
}
