package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.time.OffsetDateTime;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

public class DeactivationDateJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeactivationDate,
        DeactivationDate.DeactivationDateBuilder> {

  public DeactivationDateJsonDeserializer() {
    super(DeactivationDate.kmipTag, DeactivationDate.encodingType);
  }

  @Override
  protected DeactivationDate.DeactivationDateBuilder createBuilder() {
    return DeactivationDate.builder();
  }

  @Override
  protected void setValue(DeactivationDate.DeactivationDateBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, OffsetDateTime.class));
  }

  @Override
  protected DeactivationDate build(DeactivationDate.DeactivationDateBuilder builder) {
    return builder.build();
  }
}
