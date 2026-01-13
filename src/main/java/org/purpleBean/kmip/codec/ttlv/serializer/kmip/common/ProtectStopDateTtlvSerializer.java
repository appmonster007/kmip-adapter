package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvSerializer() {
        super(ProtectStopDate::getValue);
    }
}