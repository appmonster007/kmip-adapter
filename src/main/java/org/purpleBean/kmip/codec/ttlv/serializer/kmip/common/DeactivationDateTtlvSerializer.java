package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateTtlvSerializer() {
        super(DeactivationDate::getValue);
    }
}