package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateXmlSerializer() {
        super(OriginalCreationDate::getValue);
    }
}