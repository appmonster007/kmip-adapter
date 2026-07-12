package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.InteropFunction;

import java.io.IOException;

public class InteropFunctionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InteropFunction, InteropFunction.InteropFunctionBuilder> {

    public InteropFunctionXmlDeserializer() {
        super(InteropFunction.kmipTag, InteropFunction.encodingType);
    }

    @Override
    protected InteropFunction.InteropFunctionBuilder createBuilder() {
        return InteropFunction.builder();
    }

    @Override
    protected void setValue(InteropFunction.InteropFunctionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(InteropFunction.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected InteropFunction build(InteropFunction.InteropFunctionBuilder builder) {
        return builder.build();
    }
}