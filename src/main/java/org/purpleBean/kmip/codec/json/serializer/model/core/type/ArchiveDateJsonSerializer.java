package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateJsonSerializer() {
        super(ArchiveDate::getValue);
    }
}