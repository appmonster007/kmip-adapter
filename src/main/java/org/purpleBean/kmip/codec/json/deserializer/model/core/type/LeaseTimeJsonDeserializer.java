package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.LeaseTime;

public class LeaseTimeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<LeaseTime, LeaseTime.LeaseTimeBuilder> {

  public LeaseTimeJsonDeserializer() {
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
