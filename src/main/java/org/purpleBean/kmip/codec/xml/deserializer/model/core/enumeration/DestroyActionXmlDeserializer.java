package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

import java.io.IOException;

public class DestroyActionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DestroyAction, DestroyAction.DestroyActionBuilder> {

    public DestroyActionXmlDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType);
    }

    @Override
    protected DestroyAction.DestroyActionBuilder createBuilder() {
        return DestroyAction.builder();
    }

    @Override
    protected void setValue(DestroyAction.DestroyActionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(DestroyAction.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected DestroyAction build(DestroyAction.DestroyActionBuilder builder) {
        return builder.build();
    }
}