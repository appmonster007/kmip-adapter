package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateJsonSerializer() {
        super(ActivationDate::getValue);
    }
}