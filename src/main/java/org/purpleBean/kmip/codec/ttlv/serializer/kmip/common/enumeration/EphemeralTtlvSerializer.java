package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Ephemeral, Integer> {

    public EphemeralTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}