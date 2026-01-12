package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateJsonSerializer extends AbstractKmipJsonSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateJsonSerializer() {
        super(ValidityDate::getValue);
    }
}