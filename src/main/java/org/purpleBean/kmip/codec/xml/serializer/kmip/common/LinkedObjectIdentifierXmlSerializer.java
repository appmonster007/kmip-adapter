package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierXmlSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}