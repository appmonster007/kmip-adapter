package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.time.OffsetDateTime;

public class ProtectStopDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtectStopDate, OffsetDateTime> {

    public ProtectStopDateXmlSerializer() {
        super(ProtectStopDate::getValue);
    }
}