package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateTtlvSerializer() {
        super(DestroyDate::getValue);
    }
}