package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateXmlSerializer() {
        super(ArchiveDate::getValue);
    }
}