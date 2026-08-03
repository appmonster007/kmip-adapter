package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ExtensionType, ExtensionType.ExtensionTypeBuilder> {

  public ExtensionTypeJsonDeserializer() {
    super(ExtensionType.kmipTag, ExtensionType.encodingType);
  }

  @Override
  protected ExtensionType.ExtensionTypeBuilder createBuilder() {
    return ExtensionType.builder();
  }

  @Override
  protected void setValue(ExtensionType.ExtensionTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ExtensionType build(ExtensionType.ExtensionTypeBuilder builder) {
    return builder.build();
  }
}
