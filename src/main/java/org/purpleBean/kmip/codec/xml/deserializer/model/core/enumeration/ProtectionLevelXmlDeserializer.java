package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionLevel, String> {

    public ProtectionLevelXmlDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, String.class, value -> ProtectionLevel.fromName(value).inst());
    }
}