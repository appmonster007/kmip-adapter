package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectionLevel, Integer> {

    public ProtectionLevelTtlvDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, Integer.class, value -> ProtectionLevel.fromValue(value).inst());
    }
}