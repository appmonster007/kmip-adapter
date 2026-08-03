package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.LastChangeDate;

/**
 * XML deserializer for {@link LastChangeDate}.
 */
public class LastChangeDateXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LastChangeDate, LastChangeDate.LastChangeDateBuilder> {

  /**
   * Constructs a new {@link LastChangeDateXmlDeserializer}.
   */
  public LastChangeDateXmlDeserializer() {
    super(LastChangeDate.kmipTag, LastChangeDate.encodingType);
  }

  @Override
  protected LastChangeDate.LastChangeDateBuilder createBuilder() {
    return LastChangeDate.builder();
  }

  @Override
  protected void setValue(LastChangeDate.LastChangeDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected LastChangeDate build(LastChangeDate.LastChangeDateBuilder builder) {
    return builder.build();
  }
}