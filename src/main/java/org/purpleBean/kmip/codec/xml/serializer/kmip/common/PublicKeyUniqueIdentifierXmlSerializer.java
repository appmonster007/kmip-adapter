package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class PublicKeyUniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}