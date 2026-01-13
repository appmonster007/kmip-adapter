package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RotateNameType, String> {

    public RotateNameTypeJsonDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, String.class, value -> new RotateNameType(RotateNameType.fromName(value)));
    }
}