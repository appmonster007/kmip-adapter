package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class PrivateKeyUniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierXmlSerializer() {
        super(PrivateKeyUniqueIdentifier::getValue);
    }
}