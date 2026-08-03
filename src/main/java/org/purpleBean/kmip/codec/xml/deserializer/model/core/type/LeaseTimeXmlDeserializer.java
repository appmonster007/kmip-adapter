package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.LeaseTime;

public class LeaseTimeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<LeaseTime, LeaseTime.LeaseTimeBuilder> {

  public LeaseTimeXmlDeserializer() {
    super(LeaseTime.kmipTag, LeaseTime.encodingType);
  }

  @Override
  protected LeaseTime.LeaseTimeBuilder createBuilder() {
    return LeaseTime.builder();
  }

  @Override
  protected void setValue(LeaseTime.LeaseTimeBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected LeaseTime build(LeaseTime.LeaseTimeBuilder builder) {
    return builder.build();
  }
}