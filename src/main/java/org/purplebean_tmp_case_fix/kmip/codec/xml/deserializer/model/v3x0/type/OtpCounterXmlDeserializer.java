package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpCounter;

public class OtpCounterXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

  public OtpCounterXmlDeserializer() {
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
