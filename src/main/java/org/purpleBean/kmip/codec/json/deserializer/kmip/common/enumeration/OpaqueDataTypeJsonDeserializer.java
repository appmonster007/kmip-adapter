package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeJsonDeserializer extends AbstractKmipJsonDeserializer<OpaqueDataType, String> {

    public OpaqueDataTypeJsonDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, String.class, value -> new OpaqueDataType(OpaqueDataType.fromName(value)));
    }
}