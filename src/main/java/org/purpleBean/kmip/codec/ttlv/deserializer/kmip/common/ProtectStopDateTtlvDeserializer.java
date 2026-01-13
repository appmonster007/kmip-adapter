package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType, OffsetDateTime.class, value -> ProtectStopDate.builder().value(value).build());
    }
}