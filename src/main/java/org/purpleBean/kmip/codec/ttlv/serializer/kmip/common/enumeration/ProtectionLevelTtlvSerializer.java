package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelTtlvSerializer extends AbstractKmipTtlvSerializer<ProtectionLevel, Integer> {

    public ProtectionLevelTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}