package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OperationPolicyName, String> {

    public OperationPolicyNameTtlvDeserializer() {
        super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType, String.class, value -> OperationPolicyName.builder().value(value).build());
    }
}