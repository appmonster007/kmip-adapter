package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlSerializer() {
        super(UniqueIdentifier::getValue);
    }
}