package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RevokeOpResponsePayload;

public class RevokeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RevokeOpResponsePayload,
        RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder> {

  public RevokeOpResponsePayloadJsonDeserializer() {
    super(RevokeOpResponsePayload.kmipTag, RevokeOpResponsePayload.encodingType);
  }

  @Override
  protected RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder createBuilder() {
    return RevokeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RevokeOpResponsePayload build(
      RevokeOpResponsePayload.RevokeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
