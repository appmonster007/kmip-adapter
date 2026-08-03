package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetConstraintsOpResponsePayload;

public class GetConstraintsOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetConstraintsOpResponsePayload,
        GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder> {

  public GetConstraintsOpResponsePayloadJsonDeserializer() {
    super(GetConstraintsOpResponsePayload.kmipTag, GetConstraintsOpResponsePayload.encodingType);
  }

  @Override
  protected GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder createBuilder() {
    return GetConstraintsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CONSTRAINTS ->
          builder.constraints(ctxt.readValue(p, Constraints.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetConstraintsOpResponsePayload build(
      GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}