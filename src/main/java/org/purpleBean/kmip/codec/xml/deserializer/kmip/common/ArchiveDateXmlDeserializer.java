package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateXmlDeserializer extends AbstractKmipXmlDeserializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateXmlDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType, OffsetDateTime.class, value -> ArchiveDate.builder().value(value).build());
    }
}