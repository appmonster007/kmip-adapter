package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetUsageAllocationOpResponsePayload;

/**
 * JSON deserializer for {@link GetUsageAllocationOpResponsePayload}.
 */
public class GetUsageAllocationOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetUsageAllocationOpResponsePayload,
        GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetUsageAllocationOpResponsePayloadJsonDeserializer}.
   */
  public GetUsageAllocationOpResponsePayloadJsonDeserializer() {
    super(GetUsageAllocationOpResponsePayload.kmipTag,
        GetUsageAllocationOpResponsePayload.encodingType);
  }

  @Override
  protected GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder
      createBuilder() {
    return GetUsageAllocationOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetUsageAllocationOpResponsePayload build(
      GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
