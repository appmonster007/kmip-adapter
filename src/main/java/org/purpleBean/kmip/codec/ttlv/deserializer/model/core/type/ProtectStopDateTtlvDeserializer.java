package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateTtlvDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType, OffsetDateTime.class, value -> ProtectStopDate.builder().value(value).build());
    }
}