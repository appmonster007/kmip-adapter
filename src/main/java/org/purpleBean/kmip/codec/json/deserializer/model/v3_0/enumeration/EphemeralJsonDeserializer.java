package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.enumeration.Ephemeral;

public class EphemeralJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

  public EphemeralJsonDeserializer() {
    super(Ephemeral.kmipTag, Ephemeral.encodingType);
  }

  @Override
  protected Ephemeral.EphemeralBuilder createBuilder() {
    return Ephemeral.builder();
  }

  @Override
  protected void setValue(Ephemeral.EphemeralBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(Ephemeral.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
    return builder.build();
  }
}
