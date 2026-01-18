package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType, Integer.class, value -> KeyPartIdentifier.builder().value(value).build());
    }
}