package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.io.IOException;

public class OperationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Operation, Operation.OperationBuilder> {

    public OperationJsonDeserializer() {
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
