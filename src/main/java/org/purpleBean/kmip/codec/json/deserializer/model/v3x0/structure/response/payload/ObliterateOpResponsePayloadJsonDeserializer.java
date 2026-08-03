package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;

public class ObliterateOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ObliterateOpResponsePayload,
        ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

  public ObliterateOpResponsePayloadJsonDeserializer() {
    super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
  }

  @Override
  protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
    return ObliterateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected ObliterateOpResponsePayload build(
      ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}