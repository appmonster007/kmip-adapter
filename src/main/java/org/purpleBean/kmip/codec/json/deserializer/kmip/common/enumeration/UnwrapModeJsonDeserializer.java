package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeJsonDeserializer extends AbstractKmipJsonDeserializer<UnwrapMode, String> {

    public UnwrapModeJsonDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, String.class, value -> new UnwrapMode(UnwrapMode.fromName(value)));
    }
}