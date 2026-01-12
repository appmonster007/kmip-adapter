package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateJsonSerializer extends AbstractKmipJsonSerializer<ActivationDate, OffsetDateTime> {

    public ActivationDateJsonSerializer() {
        super(ActivationDate::getValue);
    }
}