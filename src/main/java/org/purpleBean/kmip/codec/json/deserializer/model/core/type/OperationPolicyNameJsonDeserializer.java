package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

import java.io.IOException;

public class OperationPolicyNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OperationPolicyName, OperationPolicyName.OperationPolicyNameBuilder> {

    public OperationPolicyNameJsonDeserializer() {
        super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType);
    }

    @Override
    protected OperationPolicyName.OperationPolicyNameBuilder createBuilder() {
        return OperationPolicyName.builder();
    }

    @Override
    protected void setValue(OperationPolicyName.OperationPolicyNameBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected OperationPolicyName build(OperationPolicyName.OperationPolicyNameBuilder builder) {
        return builder.build();
    }
}
