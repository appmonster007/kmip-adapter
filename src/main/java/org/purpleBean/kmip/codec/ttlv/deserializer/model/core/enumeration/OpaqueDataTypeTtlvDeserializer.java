package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OpaqueDataType, Integer> {

    public OpaqueDataTypeTtlvDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, Integer.class, value -> OpaqueDataType.fromValue(value).inst());
    }
}