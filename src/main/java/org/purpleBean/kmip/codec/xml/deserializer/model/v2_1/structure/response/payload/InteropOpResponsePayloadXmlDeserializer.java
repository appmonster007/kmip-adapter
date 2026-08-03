package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.InteropOpResponsePayload;

public class InteropOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<InteropOpResponsePayload,
        InteropOpResponsePayload.InteropOpResponsePayloadBuilder> {

  public InteropOpResponsePayloadXmlDeserializer() {
    super(InteropOpResponsePayload.kmipTag, InteropOpResponsePayload.encodingType);
  }

  @Override
  protected InteropOpResponsePayload.InteropOpResponsePayloadBuilder createBuilder() {
    return InteropOpResponsePayload.builder();
  }

  @Override
  protected void setValue(InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected InteropOpResponsePayload build(
      InteropOpResponsePayload.InteropOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}