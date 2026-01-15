package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OperationPolicyName, String> {

    public OperationPolicyNameJsonSerializer() {
        super(OperationPolicyName::getValue);
    }
}