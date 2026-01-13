package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NameType, String> {

    public NameTypeJsonDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, String.class, value -> new NameType(NameType.fromName(value)));
    }
}