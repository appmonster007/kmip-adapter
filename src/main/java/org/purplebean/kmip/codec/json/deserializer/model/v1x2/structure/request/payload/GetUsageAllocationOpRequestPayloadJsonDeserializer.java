package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetUsageAllocationOpRequestPayload;

/**
 * JSON deserializer for {@link GetUsageAllocationOpRequestPayload}.
 */
public class GetUsageAllocationOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetUsageAllocationOpRequestPayload,
        GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetUsageAllocationOpRequestPayloadJsonDeserializer}.
   */
  public GetUsageAllocationOpRequestPayloadJsonDeserializer() {
    super(GetUsageAllocationOpRequestPayload.kmipTag,
        GetUsageAllocationOpRequestPayload.encodingType);
  }

  @Override
  protected GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder
      createBuilder() {
    return GetUsageAllocationOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.USAGE_LIMITS_COUNT ->
          builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetUsageAllocationOpRequestPayload build(
      GetUsageAllocationOpRequestPayload.GetUsageAllocationOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
