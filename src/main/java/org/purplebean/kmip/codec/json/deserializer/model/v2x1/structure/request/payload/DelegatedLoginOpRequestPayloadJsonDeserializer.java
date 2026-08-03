package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.structure.Rights;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * JSON deserializer for {@link DelegatedLoginOpRequestPayload}.
 */
public class DelegatedLoginOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DelegatedLoginOpRequestPayload,
        DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link DelegatedLoginOpRequestPayloadJsonDeserializer}.
   */
  public DelegatedLoginOpRequestPayloadJsonDeserializer() {
    super(DelegatedLoginOpRequestPayload.kmipTag, DelegatedLoginOpRequestPayload.encodingType);
  }

  @Override
  protected DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder createBuilder() {
    return DelegatedLoginOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(ctxt.readValue(p, LeaseTime.class));
      case KmipTag.Standard.REQUEST_COUNT ->
          builder.requestCount(ctxt.readValue(p, RequestCount.class));
      case KmipTag.Standard.USAGE_LIMITS ->
          builder.usageLimits(ctxt.readValue(p, UsageLimits.class));
      case KmipTag.Standard.RIGHTS -> builder.rights(ctxt.readValue(p, Rights.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DelegatedLoginOpRequestPayload build(
      DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
