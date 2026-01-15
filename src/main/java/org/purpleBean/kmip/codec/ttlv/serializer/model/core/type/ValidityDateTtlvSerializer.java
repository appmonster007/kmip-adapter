package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateTtlvSerializer() {
        super(ValidityDate::getValue);
    }
}