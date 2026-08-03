package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;

/**
 * JSON deserializer for {@link UsageLimits}.
 */
public class UsageLimitsJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<UsageLimits, UsageLimits.UsageLimitsBuilder> {

  /**
   * Constructs a new {@link UsageLimitsJsonDeserializer}.
   */
  public UsageLimitsJsonDeserializer() {
    super(UsageLimits.kmipTag, UsageLimits.encodingType);
  }

  @Override
  protected UsageLimits.UsageLimitsBuilder createBuilder() {
    return UsageLimits.builder();
  }

  @Override
  protected void setValue(UsageLimits.UsageLimitsBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
          builder.usageLimitsTotal(ctxt.readValue(p, UsageLimitsTotal.class));
      case KmipTag.Standard.USAGE_LIMITS_COUNT ->
          builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
      case KmipTag.Standard.USAGE_LIMITS_UNIT ->
          builder.usageLimitsUnit(ctxt.readValue(p, UsageLimitsUnit.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected UsageLimits build(UsageLimits.UsageLimitsBuilder builder) {
    return builder.build();
  }
}