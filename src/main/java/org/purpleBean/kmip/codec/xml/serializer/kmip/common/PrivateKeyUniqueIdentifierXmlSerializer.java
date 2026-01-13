package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierXmlSerializer() {
        super(PrivateKeyUniqueIdentifier::getValue);
    }
}