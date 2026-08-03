package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CertificateLength;

/**
 * XML deserializer for {@link CertificateLength}.
 */
public class CertificateLengthXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateLength,
        CertificateLength.CertificateLengthBuilder> {

  /**
   * Constructs a new {@link CertificateLengthXmlDeserializer}.
   */
  public CertificateLengthXmlDeserializer() {
    super(CertificateLength.kmipTag, CertificateLength.encodingType);
  }

  @Override
  protected CertificateLength.CertificateLengthBuilder createBuilder() {
    return CertificateLength.builder();
  }

  @Override
  protected void setValue(CertificateLength.CertificateLengthBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected CertificateLength build(CertificateLength.CertificateLengthBuilder builder) {
    return builder.build();
  }
}