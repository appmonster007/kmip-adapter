package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateTtlvSerializer extends AbstractKmipTtlvSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateTtlvSerializer() {
        super(DeactivationDate::getValue);
    }
}