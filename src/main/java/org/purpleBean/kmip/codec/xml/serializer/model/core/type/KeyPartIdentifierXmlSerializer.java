package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierXmlSerializer() {
        super(KeyPartIdentifier::getValue);
    }
}