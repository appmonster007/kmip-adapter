package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.RotateInterval;

public class RotateIntervalJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RotateInterval, RotateInterval.RotateIntervalBuilder> {

  public RotateIntervalJsonDeserializer() {
    super(RotateInterval.kmipTag, RotateInterval.encodingType);
  }

  @Override
  protected RotateInterval.RotateIntervalBuilder createBuilder() {
    return RotateInterval.builder();
  }

  @Override
  protected void setValue(RotateInterval.RotateIntervalBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected RotateInterval build(RotateInterval.RotateIntervalBuilder builder) {
    return builder.build();
  }
}