package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3x0.type.OtpInterval;

public class OtpIntervalXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpInterval, OtpInterval.OtpIntervalBuilder> {

  public OtpIntervalXmlDeserializer() {
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
