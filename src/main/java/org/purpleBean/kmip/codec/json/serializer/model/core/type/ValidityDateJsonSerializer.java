package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidityDate, OffsetDateTime> {

    public ValidityDateJsonSerializer() {
        super(ValidityDate::getValue);
    }
}