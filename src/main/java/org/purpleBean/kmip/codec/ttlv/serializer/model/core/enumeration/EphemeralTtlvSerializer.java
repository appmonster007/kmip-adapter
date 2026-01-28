package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

public class EphemeralTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Ephemeral, Integer> {

    public EphemeralTtlvSerializer() {
        super(Ephemeral::getIntValue);
    }
}