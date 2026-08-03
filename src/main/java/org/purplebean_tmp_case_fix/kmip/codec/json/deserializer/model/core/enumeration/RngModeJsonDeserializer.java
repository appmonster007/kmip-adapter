package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.RngMode;

public class RngModeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RngMode, RngMode.RngModeBuilder> {

  public RngModeJsonDeserializer() {
    super(RngMode.kmipTag, RngMode.encodingType);
  }

  @Override
  protected RngMode.RngModeBuilder createBuilder() {
    return RngMode.builder();
  }

  @Override
  protected void setValue(RngMode.RngModeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(RngMode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RngMode build(RngMode.RngModeBuilder builder) {
    return builder.build();
  }
}
