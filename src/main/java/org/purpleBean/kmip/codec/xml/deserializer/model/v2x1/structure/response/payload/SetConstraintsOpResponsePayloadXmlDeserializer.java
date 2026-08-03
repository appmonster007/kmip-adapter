package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;

public class SetConstraintsOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SetConstraintsOpResponsePayload,
        SetConstraintsOpResponsePayload.SetConstraintsOpResponsePayloadBuilder> {

  public SetConstraintsOpResponsePayloadXmlDeserializer() {
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