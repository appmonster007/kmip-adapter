package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateXmlDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType, OffsetDateTime.class, value -> ProtectStopDate.builder().value(value).build());
    }
}