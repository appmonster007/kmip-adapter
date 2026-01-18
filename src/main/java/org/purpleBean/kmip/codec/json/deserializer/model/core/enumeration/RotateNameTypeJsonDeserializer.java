package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RotateNameType, String> {

    public RotateNameTypeJsonDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, String.class, value -> new RotateNameType(RotateNameType.fromName(value)));
    }
}