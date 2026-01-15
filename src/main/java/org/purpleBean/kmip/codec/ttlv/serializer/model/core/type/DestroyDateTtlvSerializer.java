package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateTtlvSerializer() {
        super(DestroyDate::getValue);
    }
}