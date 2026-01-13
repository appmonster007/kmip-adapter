package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateTtlvSerializer() {
        super(ArchiveDate::getValue);
    }
}