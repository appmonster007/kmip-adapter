package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionTag;

public class ExtensionTagJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<ExtensionTag, ExtensionTag.ExtensionTagBuilder> {

  public ExtensionTagJsonDeserializer() {
    super(ExtensionTag.kmipTag, ExtensionTag.encodingType);
  }

  @Override
  protected ExtensionTag.ExtensionTagBuilder createBuilder() {
    return ExtensionTag.builder();
  }

  @Override
  protected void setValue(ExtensionTag.ExtensionTagBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ExtensionTag build(ExtensionTag.ExtensionTagBuilder builder) {
    return builder.build();
  }
}
