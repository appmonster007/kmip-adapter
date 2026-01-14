package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtectionLevel, Integer> {

    public ProtectionLevelTtlvSerializer() {
        super(ProtectionLevel::getValue);
    }
}