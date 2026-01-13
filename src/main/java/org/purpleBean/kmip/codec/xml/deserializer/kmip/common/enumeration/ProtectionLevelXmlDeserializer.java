package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionLevel, String> {

    public ProtectionLevelXmlDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, String.class, value -> new ProtectionLevel(ProtectionLevel.fromName(value)));
    }
}