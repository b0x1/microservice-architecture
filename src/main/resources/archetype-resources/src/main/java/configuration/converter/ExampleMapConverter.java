#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration.converter;

import ${package}.service.model.ExampleMap;
import org.eclipse.microprofile.config.spi.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UnknownFormatConversionException;

public class ExampleMapConverter implements Converter<ExampleMap> {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public ExampleMap convert(String val) {
        ExampleMap result = new ExampleMap();

        for (String row : val.split("${symbol_escape}${symbol_escape}|")) {
            String[] params = row.split(":");
            // Sanity check
            if (params.length > 2 || params.length < 1) {
                LOG.warn(Arrays.toString(params));
                throw new UnknownFormatConversionException("YAML input badly formatted (probably without proper line breaks).");
            }
            result.put(params[0].trim(), params[1].trim());
        }

        LOG.info('"' + val + "${symbol_escape}" converted to " + result.toString());

        return result;
    }
}
