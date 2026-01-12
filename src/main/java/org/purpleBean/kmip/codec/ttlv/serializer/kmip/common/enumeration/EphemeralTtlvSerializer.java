package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralTtlvSerializer extends AbstractKmipTtlvSerializer<Ephemeral, Integer> {

    public EphemeralTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}