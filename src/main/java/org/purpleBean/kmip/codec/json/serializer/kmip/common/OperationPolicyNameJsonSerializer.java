package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameJsonSerializer extends AbstractKmipJsonSerializer<OperationPolicyName, String> {

    public OperationPolicyNameJsonSerializer() {
        super(OperationPolicyName::getValue);
    }
}