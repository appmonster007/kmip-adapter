package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OperationPolicyName, String> {

    public OperationPolicyNameJsonDeserializer() {
        super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType, String.class, value -> OperationPolicyName.builder().value(value).build());
    }
}