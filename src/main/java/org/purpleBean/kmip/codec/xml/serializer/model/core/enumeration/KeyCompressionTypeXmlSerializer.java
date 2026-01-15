package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeXmlSerializer() {
        super(KeyCompressionType::getDescription);
    }
}