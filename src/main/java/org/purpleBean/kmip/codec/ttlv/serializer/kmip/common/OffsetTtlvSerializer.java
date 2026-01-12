package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Offset;

public class OffsetTtlvSerializer extends AbstractKmipTtlvSerializer<Offset, Integer> {

    public OffsetTtlvSerializer() {
        super(Offset::getValue);
    }
}