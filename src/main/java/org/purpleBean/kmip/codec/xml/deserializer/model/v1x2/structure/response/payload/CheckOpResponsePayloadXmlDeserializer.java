package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CheckOpResponsePayload;

public class CheckOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CheckOpResponsePayload,
        CheckOpResponsePayload.CheckOpResponsePayloadBuilder> {

  public CheckOpResponsePayloadXmlDeserializer() {
    super(CheckOpResponsePayload.kmipTag, CheckOpResponsePayload.encodingType);
  }

  @Override
  protected CheckOpResponsePayload.CheckOpResponsePayloadBuilder createBuilder() {
    return CheckOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder, String tag,
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
  protected CheckOpResponsePayload build(
      CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
