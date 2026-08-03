package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<UnwrapMode, UnwrapMode.UnwrapModeBuilder> {

  public UnwrapModeJsonDeserializer() {
    super(UnwrapMode.kmipTag, UnwrapMode.encodingType);
  }

  @Override
  protected UnwrapMode.UnwrapModeBuilder createBuilder() {
    return UnwrapMode.builder();
  }

  @Override
  protected void setValue(UnwrapMode.UnwrapModeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(UnwrapMode.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected UnwrapMode build(UnwrapMode.UnwrapModeBuilder builder) {
    return builder.build();
  }
}
