package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonSerializer() {
        super(DeactivationDate::getValue);
    }
}