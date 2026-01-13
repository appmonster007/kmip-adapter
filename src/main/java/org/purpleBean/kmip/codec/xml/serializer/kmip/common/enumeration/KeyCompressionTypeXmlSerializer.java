package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeXmlSerializer() {
        super(KeyCompressionType::getDescription);
    }
}