package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitialDate, OffsetDateTime> {

    public InitialDateTtlvDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType, OffsetDateTime.class, value -> InitialDate.builder().value(value).build());
    }
}