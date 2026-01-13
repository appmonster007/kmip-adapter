package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateJsonSerializer() {
        super(ProtectStopDate::getValue);
    }
}