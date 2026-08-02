package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataInteger;

import java.io.IOException;

public class DataIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DataInteger, DataInteger.DataIntegerBuilder> {

    public DataIntegerXmlDeserializer() {
        super(DataInteger.kmipTag, DataInteger.encodingType);
    }

    @Override
    protected DataInteger.DataIntegerBuilder createBuilder() {
        return DataInteger.builder();
    }

    @Override
    protected void setValue(DataInteger.DataIntegerBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected DataInteger build(DataInteger.DataIntegerBuilder builder) {
        return builder.build();
    }
}
