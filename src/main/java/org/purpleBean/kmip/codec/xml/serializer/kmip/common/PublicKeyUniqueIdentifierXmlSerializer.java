package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}