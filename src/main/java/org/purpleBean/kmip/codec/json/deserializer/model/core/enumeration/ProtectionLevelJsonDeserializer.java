package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProtectionLevel, String> {

    public ProtectionLevelJsonDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, String.class, value -> ProtectionLevel.fromName(value).inst());
    }
}