package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateJsonSerializer extends AbstractKmipJsonSerializer<DestroyDate, OffsetDateTime> {

    public DestroyDateJsonSerializer() {
        super(DestroyDate::getValue);
    }
}