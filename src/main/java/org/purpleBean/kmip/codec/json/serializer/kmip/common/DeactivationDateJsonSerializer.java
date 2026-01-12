package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonSerializer extends AbstractKmipJsonSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonSerializer() {
        super(DeactivationDate::getValue);
    }
}