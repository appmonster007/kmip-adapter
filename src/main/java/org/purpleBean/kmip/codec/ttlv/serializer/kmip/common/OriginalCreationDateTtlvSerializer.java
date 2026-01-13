package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvSerializer() {
        super(OriginalCreationDate::getValue);
    }
}