package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.OriginalCreationDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class OriginalCreationDateXmlSerializer extends AbstractKmipXmlSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateXmlSerializer() {
        super(OriginalCreationDate::getValue);
    }
}