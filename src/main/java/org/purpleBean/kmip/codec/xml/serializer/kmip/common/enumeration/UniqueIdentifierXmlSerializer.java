package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class UniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlSerializer() {
        super(UniqueIdentifier::getDescription);
    }
}