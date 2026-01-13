package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateTtlvSerializer() {
        super(LastChangeDate::getValue);
    }
}