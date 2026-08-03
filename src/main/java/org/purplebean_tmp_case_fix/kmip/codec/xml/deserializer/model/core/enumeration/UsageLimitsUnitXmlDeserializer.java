package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UsageLimitsUnit, UsageLimitsUnit.UsageLimitsUnitBuilder> {

  public UsageLimitsUnitXmlDeserializer() {
    super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType);
  }

  @Override
  protected UsageLimitsUnit.UsageLimitsUnitBuilder createBuilder() {
    return UsageLimitsUnit.builder();
  }

  @Override
  protected void setValue(UsageLimitsUnit.UsageLimitsUnitBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(UsageLimitsUnit.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected UsageLimitsUnit build(UsageLimitsUnit.UsageLimitsUnitBuilder builder) {
    return builder.build();
  }
}