package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

public class EphemeralJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Ephemeral, String> {

    public EphemeralJsonSerializer() {
        super(Ephemeral::getDescription);
    }
}