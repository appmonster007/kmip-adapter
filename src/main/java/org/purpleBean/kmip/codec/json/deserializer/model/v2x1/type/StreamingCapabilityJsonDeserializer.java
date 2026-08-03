package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.StreamingCapability;

public class StreamingCapabilityJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<StreamingCapability,
        StreamingCapability.StreamingCapabilityBuilder> {

  public StreamingCapabilityJsonDeserializer() {
    super(StreamingCapability.kmipTag, StreamingCapability.encodingType);
  }

  @Override
  protected StreamingCapability.StreamingCapabilityBuilder createBuilder() {
    return StreamingCapability.builder();
  }

  @Override
  protected void setValue(StreamingCapability.StreamingCapabilityBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected StreamingCapability build(StreamingCapability.StreamingCapabilityBuilder builder) {
    return builder.build();
  }
}