package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateJsonDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType, OffsetDateTime.class, value -> ArchiveDate.builder().value(value).build());
    }
}