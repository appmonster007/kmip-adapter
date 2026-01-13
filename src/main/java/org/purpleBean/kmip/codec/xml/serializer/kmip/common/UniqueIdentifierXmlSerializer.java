package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlSerializer() {
        super(UniqueIdentifier::getValue);
    }
}