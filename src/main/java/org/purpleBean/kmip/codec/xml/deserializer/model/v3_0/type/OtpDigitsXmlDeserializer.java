package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3_0.type.OtpDigits;

public class OtpDigitsXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpDigits, OtpDigits.OtpDigitsBuilder> {

  public OtpDigitsXmlDeserializer() {
    super(OtpDigits.kmipTag, OtpDigits.encodingType);
  }

  @Override
  protected OtpDigits.OtpDigitsBuilder createBuilder() {
    return OtpDigits.builder();
  }

  @Override
  protected void setValue(OtpDigits.OtpDigitsBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected OtpDigits build(OtpDigits.OtpDigitsBuilder builder) {
    return builder.build();
  }
}
