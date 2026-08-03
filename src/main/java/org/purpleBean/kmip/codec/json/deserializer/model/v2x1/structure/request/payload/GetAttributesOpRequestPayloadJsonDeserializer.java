package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetAttributesOpRequestPayload;

public class GetAttributesOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetAttributesOpRequestPayload,
        GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder> {

  public GetAttributesOpRequestPayloadJsonDeserializer() {
    super(GetAttributesOpRequestPayload.kmipTag, GetAttributesOpRequestPayload.encodingType);
  }

  @Override
  protected GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder createBuilder() {
    return GetAttributesOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE_REFERENCE ->
          builder.attributeReference(ctxt.readValue(p, KmipDataType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetAttributesOpRequestPayload build(
      GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
