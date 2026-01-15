package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NameType, String> {

    public NameTypeJsonDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, String.class, value -> new NameType(NameType.fromName(value)));
    }
}