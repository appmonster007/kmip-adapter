package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelJsonDeserializer extends AbstractKmipJsonDeserializer<ProtectionLevel, String> {

    public ProtectionLevelJsonDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, String.class, value -> new ProtectionLevel(ProtectionLevel.fromName(value)));
    }
}