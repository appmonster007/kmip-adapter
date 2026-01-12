package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateTtlvSerializer extends AbstractKmipTtlvSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateTtlvSerializer() {
        super(DestroyDate::getValue);
    }
}