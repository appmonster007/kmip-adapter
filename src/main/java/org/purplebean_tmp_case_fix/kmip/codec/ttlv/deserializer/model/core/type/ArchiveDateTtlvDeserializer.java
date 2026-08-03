package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ArchiveDate;

public class ArchiveDateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ArchiveDate, ArchiveDate.ArchiveDateBuilder> {

  public ArchiveDateTtlvDeserializer() {
    super(ArchiveDate.kmipTag, ArchiveDate.encodingType);
  }

  @Override
  protected ArchiveDate.ArchiveDateBuilder createBuilder() {
    return ArchiveDate.builder();
  }

  @Override
  protected void setValue(ArchiveDate.ArchiveDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected ArchiveDate build(ArchiveDate.ArchiveDateBuilder builder) {
    return builder.build();
  }
}