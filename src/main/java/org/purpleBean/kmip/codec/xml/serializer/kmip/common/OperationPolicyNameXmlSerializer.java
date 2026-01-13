package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OperationPolicyName, String> {

    public OperationPolicyNameXmlSerializer() {
        super(OperationPolicyName::getValue);
    }
}