package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeJsonDeserializer extends AbstractKmipJsonDeserializer<RotateNameType, String> {

    public RotateNameTypeJsonDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, String.class, value -> new RotateNameType(RotateNameType.fromName(value)));
    }
}