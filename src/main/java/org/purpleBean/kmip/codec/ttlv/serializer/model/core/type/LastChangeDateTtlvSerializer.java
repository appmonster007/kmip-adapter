package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateTtlvSerializer() {
        super(LastChangeDate::getValue);
    }
}