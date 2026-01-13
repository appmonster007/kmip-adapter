package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateXmlSerializer() {
        super(ProtectStopDate::getValue);
    }
}