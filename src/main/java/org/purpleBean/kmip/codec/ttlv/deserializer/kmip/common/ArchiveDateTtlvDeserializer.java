package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateTtlvDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType, OffsetDateTime.class, value -> ArchiveDate.builder().value(value).build());
    }
}