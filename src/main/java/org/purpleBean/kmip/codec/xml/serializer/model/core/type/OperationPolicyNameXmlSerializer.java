package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;

public class OperationPolicyNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OperationPolicyName, String> {

    public OperationPolicyNameXmlSerializer() {
        super(OperationPolicyName::getValue);
    }
}