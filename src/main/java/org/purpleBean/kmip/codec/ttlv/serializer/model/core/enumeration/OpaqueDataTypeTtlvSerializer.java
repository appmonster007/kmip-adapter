package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OpaqueDataType, Integer> {

    public OpaqueDataTypeTtlvSerializer() {
        super(OpaqueDataType::getValue);
    }
}