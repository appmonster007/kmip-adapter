package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateTtlvSerializer() {
        super(ValidityDate::getValue);
    }
}