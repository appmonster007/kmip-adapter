package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;

public class SetDefaultsOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<SetDefaultsOpResponsePayload,
        SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder> {

  public SetDefaultsOpResponsePayloadXmlDeserializer() {
    super(SetDefaultsOpResponsePayload.kmipTag, SetDefaultsOpResponsePayload.encodingType);
  }

  @Override
  protected SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder createBuilder() {
    return SetDefaultsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected SetDefaultsOpResponsePayload build(
      SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}