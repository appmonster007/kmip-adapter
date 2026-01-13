package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateXmlSerializer() {
        super(OriginalCreationDate::getValue);
    }
}