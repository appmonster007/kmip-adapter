package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SetDefaultsOpRequestPayload;

public class SetDefaultsOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SetDefaultsOpRequestPayload,
        SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder> {

  public SetDefaultsOpRequestPayloadJsonDeserializer() {
    super(SetDefaultsOpRequestPayload.kmipTag, SetDefaultsOpRequestPayload.encodingType);
  }

  @Override
  protected SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder createBuilder() {
    return SetDefaultsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DEFAULTS_INFORMATION ->
          builder.defaultsInformation(ctxt.readValue(p, DefaultsInformation.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetDefaultsOpRequestPayload build(
      SetDefaultsOpRequestPayload.SetDefaultsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
