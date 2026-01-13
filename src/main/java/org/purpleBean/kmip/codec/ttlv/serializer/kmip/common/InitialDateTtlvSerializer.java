package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitialDate, OffsetDateTime> {

    public InitialDateTtlvSerializer() {
        super(InitialDate::getValue);
    }
}