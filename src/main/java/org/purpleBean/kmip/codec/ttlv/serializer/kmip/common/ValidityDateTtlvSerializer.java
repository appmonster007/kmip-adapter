package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateTtlvSerializer extends AbstractKmipTtlvSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateTtlvSerializer() {
        super(ValidityDate::getValue);
    }
}