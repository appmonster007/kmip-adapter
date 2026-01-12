package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateXmlSerializer extends AbstractKmipXmlSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateXmlSerializer() {
        super(ProtectStopDate::getValue);
    }
}