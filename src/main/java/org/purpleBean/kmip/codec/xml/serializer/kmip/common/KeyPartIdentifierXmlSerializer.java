package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}