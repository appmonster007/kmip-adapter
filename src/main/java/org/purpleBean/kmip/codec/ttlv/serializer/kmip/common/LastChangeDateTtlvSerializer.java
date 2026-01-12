package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateTtlvSerializer extends AbstractKmipTtlvSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateTtlvSerializer() {
        super(LastChangeDate::getValue);
    }
}