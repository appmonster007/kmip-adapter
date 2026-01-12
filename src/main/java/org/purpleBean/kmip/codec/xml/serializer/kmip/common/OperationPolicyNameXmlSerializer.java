package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.OperationPolicyName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class OperationPolicyNameXmlSerializer extends AbstractKmipXmlSerializer<OperationPolicyName, String> {

    public OperationPolicyNameXmlSerializer() {
        super(OperationPolicyName::getValue);
    }
}