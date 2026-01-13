package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OperationPolicyName, String> {

    public OperationPolicyNameTtlvSerializer() {
        super(OperationPolicyName::getValue);
    }
}