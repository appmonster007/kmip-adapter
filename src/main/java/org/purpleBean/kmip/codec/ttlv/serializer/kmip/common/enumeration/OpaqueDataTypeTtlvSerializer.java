package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeTtlvSerializer extends AbstractKmipTtlvSerializer<OpaqueDataType, Integer> {

    public OpaqueDataTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}