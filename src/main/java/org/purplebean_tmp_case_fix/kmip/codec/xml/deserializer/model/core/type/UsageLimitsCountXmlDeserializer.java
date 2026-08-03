package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UsageLimitsCount,
        UsageLimitsCount.UsageLimitsCountBuilder> {

  public UsageLimitsCountXmlDeserializer() {
    super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType);
  }

  @Override
  protected UsageLimitsCount.UsageLimitsCountBuilder createBuilder() {
    return UsageLimitsCount.builder();
  }

  @Override
  protected void setValue(UsageLimitsCount.UsageLimitsCountBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Long.class));
  }

  @Override
  protected UsageLimitsCount build(UsageLimitsCount.UsageLimitsCountBuilder builder) {
    return builder.build();
  }
}