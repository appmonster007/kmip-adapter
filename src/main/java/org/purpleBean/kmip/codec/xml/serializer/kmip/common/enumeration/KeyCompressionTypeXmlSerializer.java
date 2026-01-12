package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.KeyCompressionType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class KeyCompressionTypeXmlSerializer extends AbstractKmipXmlSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeXmlSerializer() {
        super(KeyCompressionType::getDescription);
    }
}