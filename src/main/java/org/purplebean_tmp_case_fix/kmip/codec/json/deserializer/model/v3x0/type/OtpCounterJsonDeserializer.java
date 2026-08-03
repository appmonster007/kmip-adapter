package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpCounter;

public class OtpCounterJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

  public OtpCounterJsonDeserializer() {
    super(OtpCounter.kmipTag, OtpCounter.encodingType);
  }

  @Override
  protected OtpCounter.OtpCounterBuilder createBuilder() {
    return OtpCounter.builder();
  }

  @Override
  protected void setValue(OtpCounter.OtpCounterBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected OtpCounter build(OtpCounter.OtpCounterBuilder builder) {
    return builder.build();
  }
}
