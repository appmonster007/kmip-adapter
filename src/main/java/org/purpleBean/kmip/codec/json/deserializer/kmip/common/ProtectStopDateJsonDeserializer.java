package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateJsonDeserializer extends AbstractKmipJsonDeserializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateJsonDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType, OffsetDateTime.class, value -> ProtectStopDate.builder().value(value).build());
    }
}