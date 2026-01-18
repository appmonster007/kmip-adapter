package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateJsonSerializer() {
        super(OriginalCreationDate::getValue);
    }
}