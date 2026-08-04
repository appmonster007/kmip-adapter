package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ProtectionPeriod;

/**
 * JSON deserializer for {@link ProtectionPeriod}.
 */
public class ProtectionPeriodJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtectionPeriod,
        ProtectionPeriod.ProtectionPeriodBuilder> {

  /**
   * Constructs a new {@link ProtectionPeriodJsonDeserializer}.
   */
  public ProtectionPeriodJsonDeserializer() {
    super(ProtectionPeriod.kmipTag, ProtectionPeriod.encodingType);
  }

  @Override
  protected ProtectionPeriod.ProtectionPeriodBuilder createBuilder() {
    return ProtectionPeriod.builder();
  }

  @Override
  protected void setValue(ProtectionPeriod.ProtectionPeriodBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
    return builder.build();
  }
}