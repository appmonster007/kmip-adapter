package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralTtlvDeserializer extends AbstractKmipTtlvDeserializer<Ephemeral, Integer> {

    public EphemeralTtlvDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType, Integer.class, value -> new Ephemeral(Ephemeral.fromValue(value)));
    }
}