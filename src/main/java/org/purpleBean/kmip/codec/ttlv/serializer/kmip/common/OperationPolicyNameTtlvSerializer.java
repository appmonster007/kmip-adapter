package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameTtlvSerializer extends AbstractKmipTtlvSerializer<OperationPolicyName, String> {

    public OperationPolicyNameTtlvSerializer() {
        super(OperationPolicyName::getValue);
    }
}