package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CheckOpRequestPayload;

public class CheckOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CheckOpRequestPayload,
        CheckOpRequestPayload.CheckOpRequestPayloadBuilder> {

  public CheckOpRequestPayloadJsonDeserializer() {
    super(CheckOpRequestPayload.kmipTag, CheckOpRequestPayload.encodingType);
  }

  @Override
  protected CheckOpRequestPayload.CheckOpRequestPayloadBuilder createBuilder() {
    return CheckOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CheckOpRequestPayload.CheckOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.USAGE_LIMITS_COUNT ->
          builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_USAGE_MASK ->
          builder.cryptographicUsageMask(ctxt.readValue(p, CryptographicUsageMask.class));
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(ctxt.readValue(p, LeaseTime.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CheckOpRequestPayload build(
      CheckOpRequestPayload.CheckOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
