package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtectionLevel, Integer> {

    public ProtectionLevelTtlvSerializer() {
        super(ProtectionLevel::getValue);
    }
}