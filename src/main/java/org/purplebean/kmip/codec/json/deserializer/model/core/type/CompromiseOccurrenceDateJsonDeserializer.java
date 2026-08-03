package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;

public class CompromiseOccurrenceDateJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CompromiseOccurrenceDate,
        CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder> {

  public CompromiseOccurrenceDateJsonDeserializer() {
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
