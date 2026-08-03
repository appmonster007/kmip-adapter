package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AdjustAttributeOpResponsePayload;

public class AdjustAttributeOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AdjustAttributeOpResponsePayload,
        AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder> {

  public AdjustAttributeOpResponsePayloadJsonDeserializer() {
    super(AdjustAttributeOpResponsePayload.kmipTag, AdjustAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder createBuilder() {
    return AdjustAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.NEW_ATTRIBUTE ->
          builder.newAttribute(ctxt.readValue(p, NewAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AdjustAttributeOpResponsePayload build(
      AdjustAttributeOpResponsePayload.AdjustAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}