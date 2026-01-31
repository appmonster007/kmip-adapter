package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

import java.io.IOException;

public class InitialCounterValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitialCounterValue, InitialCounterValue.InitialCounterValueBuilder> {

    public InitialCounterValueXmlDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType);
    }

    @Override
    protected InitialCounterValue.InitialCounterValueBuilder createBuilder() {
        return InitialCounterValue.builder();
    }

    @Override
    protected void setValue(InitialCounterValue.InitialCounterValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected InitialCounterValue build(InitialCounterValue.InitialCounterValueBuilder builder) {
        return builder.build();
    }
}