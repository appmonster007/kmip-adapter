package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateXmlSerializer() {
        super(ArchiveDate::getValue);
    }
}