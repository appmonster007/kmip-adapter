package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateJsonSerializer() {
        super(DestroyDate::getValue);
    }
}