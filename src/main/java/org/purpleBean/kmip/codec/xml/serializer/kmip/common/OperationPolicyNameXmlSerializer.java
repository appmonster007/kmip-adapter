package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.OperationPolicyName;

public class OperationPolicyNameXmlSerializer extends AbstractKmipXmlSerializer<OperationPolicyName, String> {

    public OperationPolicyNameXmlSerializer() {
        super(OperationPolicyName::getValue);
    }
}