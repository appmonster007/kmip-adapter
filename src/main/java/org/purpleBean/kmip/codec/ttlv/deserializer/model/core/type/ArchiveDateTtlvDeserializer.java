package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ArchiveDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ArchiveDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ArchiveDate, ArchiveDate.ArchiveDateBuilder> {

    public ArchiveDateTtlvDeserializer() {
        super(ArchiveDate.kmipTag, ArchiveDate.encodingType);
    }

    @Override
    protected ArchiveDate.ArchiveDateBuilder createBuilder() {
        return ArchiveDate.builder();
    }

    @Override
    protected void setValue(ArchiveDate.ArchiveDateBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected ArchiveDate build(ArchiveDate.ArchiveDateBuilder builder) {
        return builder.build();
    }
}