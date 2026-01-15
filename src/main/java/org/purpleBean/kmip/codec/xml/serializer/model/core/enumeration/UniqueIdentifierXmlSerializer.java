package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

public class UniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlSerializer() {
        super(UniqueIdentifier::getDescription);
    }
}