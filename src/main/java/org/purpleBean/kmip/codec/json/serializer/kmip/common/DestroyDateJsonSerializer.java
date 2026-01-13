package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateJsonSerializer() {
        super(DestroyDate::getValue);
    }
}