package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OperationPolicyName, String> {

    public OperationPolicyNameXmlDeserializer() {
        super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType, String.class, value -> OperationPolicyName.builder().value(value).build());
    }
}