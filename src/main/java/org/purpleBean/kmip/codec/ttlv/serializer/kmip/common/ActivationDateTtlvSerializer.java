package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateTtlvSerializer() {
        super(ActivationDate::getValue);
    }
}