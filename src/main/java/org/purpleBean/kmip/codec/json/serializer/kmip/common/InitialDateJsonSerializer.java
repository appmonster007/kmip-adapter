package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateJsonSerializer extends AbstractKmipJsonSerializer<InitialDate, OffsetDateTime> {

    public InitialDateJsonSerializer() {
        super(InitialDate::getValue);
    }
}