package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateJsonSerializer extends AbstractKmipJsonSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateJsonSerializer() {
        super(ProtectStopDate::getValue);
    }
}