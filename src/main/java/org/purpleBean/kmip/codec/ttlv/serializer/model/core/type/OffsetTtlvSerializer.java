package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Offset;

public class OffsetTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Offset, Integer> {

    public OffsetTtlvSerializer() {
        super(Offset::getValue);
    }
}