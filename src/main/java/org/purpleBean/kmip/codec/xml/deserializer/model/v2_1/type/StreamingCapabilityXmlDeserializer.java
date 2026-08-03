package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;

public class StreamingCapabilityXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<StreamingCapability,
        StreamingCapability.StreamingCapabilityBuilder> {

  public StreamingCapabilityXmlDeserializer() {
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