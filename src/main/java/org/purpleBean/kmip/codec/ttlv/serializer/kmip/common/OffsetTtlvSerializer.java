package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Offset, Integer> {

    public OffsetTtlvSerializer() {
        super(Offset::getValue);
    }
}