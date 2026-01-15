package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NistKeyType, String> {

    public NistKeyTypeJsonDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, String.class, value -> new NistKeyType(NistKeyType.fromName(value)));
    }
}