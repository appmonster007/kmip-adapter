package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlSerializer() {
        super(PublicKeyUniqueIdentifier::getValue);
    }
}