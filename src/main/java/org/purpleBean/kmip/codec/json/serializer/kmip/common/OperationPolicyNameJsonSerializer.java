package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OperationPolicyName, String> {

    public OperationPolicyNameJsonSerializer() {
        super(OperationPolicyName::getValue);
    }
}