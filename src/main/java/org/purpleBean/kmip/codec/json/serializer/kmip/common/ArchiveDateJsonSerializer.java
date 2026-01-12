package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateJsonSerializer extends AbstractKmipJsonSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateJsonSerializer() {
        super(ArchiveDate::getValue);
    }
}