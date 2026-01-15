package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateJsonSerializer() {
        super(ProtectStopDate::getValue);
    }
}