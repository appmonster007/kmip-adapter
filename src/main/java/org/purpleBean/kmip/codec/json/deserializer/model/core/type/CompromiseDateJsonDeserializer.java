package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

public class CompromiseDateJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CompromiseDate, CompromiseDate.CompromiseDateBuilder> {

  public CompromiseDateJsonDeserializer() {
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
