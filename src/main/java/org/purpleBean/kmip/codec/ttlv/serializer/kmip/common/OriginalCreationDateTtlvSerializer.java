package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvSerializer extends AbstractKmipTtlvSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvSerializer() {
        super(OriginalCreationDate::getValue);
    }
}