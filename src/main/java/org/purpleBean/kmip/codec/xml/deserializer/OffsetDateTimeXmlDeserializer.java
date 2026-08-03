package org.purpleBean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;

public class OffsetDateTimeXmlDeserializer extends JsonDeserializer<OffsetDateTime> {

  @Override
  public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return OffsetDateTime.parse(p.getText());
  }
}