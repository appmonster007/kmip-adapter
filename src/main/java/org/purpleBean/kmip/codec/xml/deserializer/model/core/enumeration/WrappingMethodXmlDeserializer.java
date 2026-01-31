package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

import java.io.IOException;

public class WrappingMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<WrappingMethod, WrappingMethod.WrappingMethodBuilder> {

    public WrappingMethodXmlDeserializer() {
        super(WrappingMethod.kmipTag, WrappingMethod.encodingType);
    }

    @Override
    protected WrappingMethod.WrappingMethodBuilder createBuilder() {
        return WrappingMethod.builder();
    }

    @Override
    protected void setValue(WrappingMethod.WrappingMethodBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(WrappingMethod.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected WrappingMethod build(WrappingMethod.WrappingMethodBuilder builder) {
        return builder.build();
    }
}