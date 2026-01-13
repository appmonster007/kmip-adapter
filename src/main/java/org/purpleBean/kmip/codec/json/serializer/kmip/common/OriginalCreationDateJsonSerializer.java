package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateJsonSerializer() {
        super(OriginalCreationDate::getValue);
    }
}