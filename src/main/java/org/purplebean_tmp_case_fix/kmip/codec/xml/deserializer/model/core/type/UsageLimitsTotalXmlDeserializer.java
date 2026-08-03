package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsTotalXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UsageLimitsTotal,
        UsageLimitsTotal.UsageLimitsTotalBuilder> {

  public UsageLimitsTotalXmlDeserializer() {
    super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType);
  }

  @Override
  protected UsageLimitsTotal.UsageLimitsTotalBuilder createBuilder() {
    return UsageLimitsTotal.builder();
  }

  @Override
  protected void setValue(UsageLimitsTotal.UsageLimitsTotalBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected UsageLimitsTotal build(UsageLimitsTotal.UsageLimitsTotalBuilder builder) {
    return builder.build();
  }
}