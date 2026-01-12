package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvSerializer extends AbstractKmipTtlvSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvSerializer() {
        super(ProtectStopDate::getValue);
    }
}