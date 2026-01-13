package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonSerializer() {
        super(DeactivationDate::getValue);
    }
}