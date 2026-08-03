package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ProtectionPeriod;

public class ProtectionPeriodJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ProtectionPeriod,
        ProtectionPeriod.ProtectionPeriodBuilder> {

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
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
    return builder.build();
  }
}