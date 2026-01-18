package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.time.OffsetDateTime;

public class ArchiveDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ArchiveDate, OffsetDateTime> {

    public ArchiveDateXmlDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType, OffsetDateTime.class, value -> ArchiveDate.builder().value(value).build());
    }
}