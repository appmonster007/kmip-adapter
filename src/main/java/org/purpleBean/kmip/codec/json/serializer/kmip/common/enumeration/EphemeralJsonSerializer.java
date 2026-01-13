package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Ephemeral, String> {

    public EphemeralJsonSerializer() {
        super(Ephemeral::getDescription);
    }
}