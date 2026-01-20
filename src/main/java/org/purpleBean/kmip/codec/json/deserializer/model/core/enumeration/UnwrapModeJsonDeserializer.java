package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UnwrapMode, String> {

    public UnwrapModeJsonDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType, String.class, value -> UnwrapMode.fromName(value).inst());
    }
}