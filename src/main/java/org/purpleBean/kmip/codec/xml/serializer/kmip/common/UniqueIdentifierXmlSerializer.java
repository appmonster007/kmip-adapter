package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.UniqueIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class UniqueIdentifierXmlSerializer extends AbstractKmipXmlSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlSerializer() {
        super(UniqueIdentifier::getValue);
    }
}