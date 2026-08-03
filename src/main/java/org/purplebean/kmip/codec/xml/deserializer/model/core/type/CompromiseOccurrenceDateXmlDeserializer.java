package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;

/**
 * XML deserializer for {@link CompromiseOccurrenceDate}.
 */
public class CompromiseOccurrenceDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CompromiseOccurrenceDate,
        CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder> {

  /**
   * Constructs a new {@link CompromiseOccurrenceDateXmlDeserializer}.
   */
  public CompromiseOccurrenceDateXmlDeserializer() {
    super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType);
  }

  @Override
  protected CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder createBuilder() {
    return CompromiseOccurrenceDate.builder();
  }

  @Override
  protected void setValue(CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected CompromiseOccurrenceDate build(
      CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder) {
    return builder.build();
  }
}