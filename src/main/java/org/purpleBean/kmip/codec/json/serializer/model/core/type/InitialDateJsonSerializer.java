package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InitialDate, OffsetDateTime> {

    public InitialDateJsonSerializer() {
        super(InitialDate::getValue);
    }
}