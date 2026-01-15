package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateTtlvSerializer() {
        super(ActivationDate::getValue);
    }
}