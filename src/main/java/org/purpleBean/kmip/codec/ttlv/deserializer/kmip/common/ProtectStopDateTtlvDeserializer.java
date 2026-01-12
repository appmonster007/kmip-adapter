package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType, OffsetDateTime.class, value -> ProtectStopDate.builder().value(value).build());
    }
}