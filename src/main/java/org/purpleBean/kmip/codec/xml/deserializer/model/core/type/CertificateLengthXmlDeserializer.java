package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

public class CertificateLengthXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateLength,
        CertificateLength.CertificateLengthBuilder> {

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