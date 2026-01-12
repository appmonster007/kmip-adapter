package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<InitialDate, OffsetDateTime> {

    public InitialDateTtlvDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType, OffsetDateTime.class, value -> InitialDate.builder().value(value).build());
    }
}