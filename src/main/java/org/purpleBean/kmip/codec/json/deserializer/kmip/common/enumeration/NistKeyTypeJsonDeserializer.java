package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NistKeyType, String> {

    public NistKeyTypeJsonDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, String.class, value -> new NistKeyType(NistKeyType.fromName(value)));
    }
}