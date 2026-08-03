package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ArchiveDate;

/**
 * JSON deserializer for {@link ArchiveDate}.
 */
public class ArchiveDateJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ArchiveDate, ArchiveDate.ArchiveDateBuilder> {

  /**
   * Constructs a new {@link ArchiveDateJsonDeserializer}.
   */
  public ArchiveDateJsonDeserializer() {
    super(ArchiveDate.kmipTag, ArchiveDate.encodingType);
  }

  @Override
  protected ArchiveDate.ArchiveDateBuilder createBuilder() {
    return ArchiveDate.builder();
  }

  @Override
  protected void setValue(ArchiveDate.ArchiveDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ArchiveDate build(ArchiveDate.ArchiveDateBuilder builder) {
    return builder.build();
  }
}
