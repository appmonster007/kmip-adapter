package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CompromiseDate;

/**
 * XML deserializer for {@link CompromiseDate}.
 */
public class CompromiseDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CompromiseDate, CompromiseDate.CompromiseDateBuilder> {

  /**
   * Constructs a new {@link CompromiseDateXmlDeserializer}.
   */
  public CompromiseDateXmlDeserializer() {
    super(CompromiseDate.kmipTag, CompromiseDate.encodingType);
  }

  @Override
  protected CompromiseDate.CompromiseDateBuilder createBuilder() {
    return CompromiseDate.builder();
  }

  @Override
  protected void setValue(CompromiseDate.CompromiseDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected CompromiseDate build(CompromiseDate.CompromiseDateBuilder builder) {
    return builder.build();
  }
}