package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OpaqueDataType, String> {

    public OpaqueDataTypeJsonDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, String.class, value -> OpaqueDataType.fromName(value).inst());
    }
}