package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}