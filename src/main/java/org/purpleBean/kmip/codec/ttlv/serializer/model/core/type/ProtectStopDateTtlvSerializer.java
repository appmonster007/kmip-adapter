package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvSerializer() {
        super(ProtectStopDate::getValue);
    }
}