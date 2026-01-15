package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OperationPolicyName, String> {

    public OperationPolicyNameTtlvSerializer() {
        super(OperationPolicyName::getValue);
    }
}