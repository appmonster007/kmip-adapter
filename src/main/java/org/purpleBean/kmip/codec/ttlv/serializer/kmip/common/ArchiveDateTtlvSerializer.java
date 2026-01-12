package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateTtlvSerializer extends AbstractKmipTtlvSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateTtlvSerializer() {
        super(ArchiveDate::getValue);
    }
}