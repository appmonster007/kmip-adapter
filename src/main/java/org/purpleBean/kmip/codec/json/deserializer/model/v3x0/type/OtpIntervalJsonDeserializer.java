package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpInterval;

public class OtpIntervalJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OtpInterval, OtpInterval.OtpIntervalBuilder> {

  public OtpIntervalJsonDeserializer() {
    super(OtpInterval.kmipTag, OtpInterval.encodingType);
  }

  @Override
  protected OtpInterval.OtpIntervalBuilder createBuilder() {
    return OtpInterval.builder();
  }

  @Override
  protected void setValue(OtpInterval.OtpIntervalBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected OtpInterval build(OtpInterval.OtpIntervalBuilder builder) {
    return builder.build();
  }
}
