package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvSerializer() {
        super(OriginalCreationDate::getValue);
    }
}