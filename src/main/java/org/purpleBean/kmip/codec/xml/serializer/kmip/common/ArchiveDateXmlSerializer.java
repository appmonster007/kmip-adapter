package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateXmlSerializer extends AbstractKmipXmlSerializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateXmlSerializer() {
        super(ArchiveDate::getValue);
    }
}