package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.RotateLatest;

public class RotateLatestJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RotateLatest, RotateLatest.RotateLatestBuilder> {

  public RotateLatestJsonDeserializer() {
    super(RotateLatest.kmipTag, RotateLatest.encodingType);
  }

  @Override
  protected RotateLatest.RotateLatestBuilder createBuilder() {
    return RotateLatest.builder();
  }

  @Override
  protected void setValue(RotateLatest.RotateLatestBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected RotateLatest build(RotateLatest.RotateLatestBuilder builder) {
    return builder.build();
  }
}