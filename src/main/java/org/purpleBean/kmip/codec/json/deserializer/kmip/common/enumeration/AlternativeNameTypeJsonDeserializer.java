package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeJsonDeserializer extends AbstractKmipJsonDeserializer<AlternativeNameType, String> {

    public AlternativeNameTypeJsonDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, String.class, value -> new AlternativeNameType(AlternativeNameType.fromName(value)));
    }
}