package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.io.IOException;

public class OperationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Operation, Operation.OperationBuilder> {

    public OperationXmlDeserializer() {
        super(Operation.kmipTag, Operation.encodingType);
    }

    @Override
    protected Operation.OperationBuilder createBuilder() {
        return Operation.builder();
    }

    @Override
    protected void setValue(Operation.OperationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(Operation.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected Operation build(Operation.OperationBuilder builder) {
        return builder.build();
    }
}