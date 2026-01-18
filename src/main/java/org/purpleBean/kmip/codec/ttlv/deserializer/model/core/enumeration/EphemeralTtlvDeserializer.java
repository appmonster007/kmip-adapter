package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

public class EphemeralTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Ephemeral, Integer> {

    public EphemeralTtlvDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType, Integer.class, value -> new Ephemeral(Ephemeral.fromValue(value)));
    }
}