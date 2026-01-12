package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.KeyPartIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class KeyPartIdentifierXmlSerializer extends AbstractKmipXmlSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}