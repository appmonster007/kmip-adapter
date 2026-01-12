package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralJsonSerializer extends AbstractKmipJsonSerializer<Ephemeral, String> {

    public EphemeralJsonSerializer() {
        super(Ephemeral::getDescription);
    }
}