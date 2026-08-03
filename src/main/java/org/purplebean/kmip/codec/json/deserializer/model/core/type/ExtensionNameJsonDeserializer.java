package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.ExtensionName;

/**
 * JSON deserializer for {@link ExtensionName}.
 */
public class ExtensionNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ExtensionName, ExtensionName.ExtensionNameBuilder> {

  /**
   * Constructs a new {@link ExtensionNameJsonDeserializer}.
   */
  public ExtensionNameJsonDeserializer() {
    super(ExtensionName.kmipTag, ExtensionName.encodingType);
  }

  @Override
  protected ExtensionName.ExtensionNameBuilder createBuilder() {
    return ExtensionName.builder();
  }

  @Override
  protected void setValue(ExtensionName.ExtensionNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ExtensionName build(ExtensionName.ExtensionNameBuilder builder) {
    return builder.build();
  }
}
