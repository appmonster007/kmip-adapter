package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitialDate, OffsetDateTime> {

    public InitialDateTtlvSerializer() {
        super(InitialDate::getValue);
    }
}