package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AlternativeNameType, String> {

    public AlternativeNameTypeJsonDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, String.class, value -> new AlternativeNameType(AlternativeNameType.fromName(value)));
    }
}