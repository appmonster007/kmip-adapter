package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<OpaqueDataType, Integer> {

    public OpaqueDataTypeTtlvDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, Integer.class, value -> new OpaqueDataType(OpaqueDataType.fromValue(value)));
    }
}