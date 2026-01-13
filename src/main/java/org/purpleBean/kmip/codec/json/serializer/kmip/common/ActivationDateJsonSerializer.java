package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateJsonSerializer() {
        super(ActivationDate::getValue);
    }
}