package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ActivationDate;

public class ActivationDateJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ActivationDate, ActivationDate.ActivationDateBuilder> {

  public ActivationDateJsonDeserializer() {
    super(ActivationDate.kmipTag, ActivationDate.encodingType);
  }

  @Override
  protected ActivationDate.ActivationDateBuilder createBuilder() {
    return ActivationDate.builder();
  }

  @Override
  protected void setValue(ActivationDate.ActivationDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected ActivationDate build(ActivationDate.ActivationDateBuilder builder) {
    return builder.build();
  }
}