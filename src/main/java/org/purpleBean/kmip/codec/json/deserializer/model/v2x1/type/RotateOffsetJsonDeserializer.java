package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.RotateOffset;

public class RotateOffsetJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RotateOffset, RotateOffset.RotateOffsetBuilder> {

  public RotateOffsetJsonDeserializer() {
    super(RotateOffset.kmipTag, RotateOffset.encodingType);
  }

  @Override
  protected RotateOffset.RotateOffsetBuilder createBuilder() {
    return RotateOffset.builder();
  }

  @Override
  protected void setValue(RotateOffset.RotateOffsetBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected RotateOffset build(RotateOffset.RotateOffsetBuilder builder) {
    return builder.build();
  }
}