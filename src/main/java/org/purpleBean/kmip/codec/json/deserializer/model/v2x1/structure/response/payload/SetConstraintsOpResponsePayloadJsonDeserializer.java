package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;

public class SetConstraintsOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SetConstraintsOpResponsePayload,
        SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder> {

  public SetConstraintsOpResponsePayloadJsonDeserializer() {
    super(SetConstraintsOpResponsePayload.kmipTag, SetConstraintsOpResponsePayload.encodingType);
  }

  @Override
  protected SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder createBuilder() {
    return SetConstraintsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected SetConstraintsOpResponsePayload build(
      SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}