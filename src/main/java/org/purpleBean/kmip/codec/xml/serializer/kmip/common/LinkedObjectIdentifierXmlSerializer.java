package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierXmlSerializer extends AbstractKmipXmlSerializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierXmlSerializer() {
        super(LinkedObjectIdentifier::getValue);
    }
}