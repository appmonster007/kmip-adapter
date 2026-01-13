package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Ephemeral, String> {

    public EphemeralJsonDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType, String.class, value -> new Ephemeral(Ephemeral.fromName(value)));
    }
}