package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateTtlvSerializer() {
        super(ArchiveDate::getValue);
    }
}