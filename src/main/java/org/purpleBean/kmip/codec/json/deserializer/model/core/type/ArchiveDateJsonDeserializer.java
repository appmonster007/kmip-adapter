package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateJsonDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType, OffsetDateTime.class, value -> ArchiveDate.builder().value(value).build());
    }
}