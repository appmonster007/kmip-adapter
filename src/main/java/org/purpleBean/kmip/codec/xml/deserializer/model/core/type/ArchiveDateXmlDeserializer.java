package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ArchiveDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ArchiveDate, ArchiveDate.ArchiveDateBuilder> {

    public ArchiveDateXmlDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType);
    }

    @Override
    protected ArchiveDate.ArchiveDateBuilder createBuilder() {
        return ArchiveDate.builder();
    }

    @Override
    protected void setValue(ArchiveDate.ArchiveDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected ArchiveDate build(ArchiveDate.ArchiveDateBuilder builder) {
        return builder.build();
    }
}