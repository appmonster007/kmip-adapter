package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierXmlDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType, String.class, value -> LinkedObjectIdentifier.builder().value(value).build());
    }
}