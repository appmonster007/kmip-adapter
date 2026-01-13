package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitialDate, OffsetDateTime> {

    public InitialDateJsonDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType, OffsetDateTime.class, value -> InitialDate.builder().value(value).build());
    }
}