package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ArchiveDate;

/**
 * XML deserializer for {@link ArchiveDate}.
 */
public class ArchiveDateXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ArchiveDate, ArchiveDate.ArchiveDateBuilder> {

  /**
   * Constructs a new {@link ArchiveDateXmlDeserializer}.
   */
  public ArchiveDateXmlDeserializer() {
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