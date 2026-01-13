package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectionLevel, Integer> {

    public ProtectionLevelTtlvDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType, Integer.class, value -> new ProtectionLevel(ProtectionLevel.fromValue(value)));
    }
}