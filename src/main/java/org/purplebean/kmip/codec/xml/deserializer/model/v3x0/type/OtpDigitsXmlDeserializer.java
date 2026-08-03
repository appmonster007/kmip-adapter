package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;

/**
 * XML deserializer for {@link OtpDigits}.
 */
public class OtpDigitsXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpDigits, OtpDigits.OtpDigitsBuilder> {

  /**
   * Constructs a new {@link OtpDigitsXmlDeserializer}.
   */
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
