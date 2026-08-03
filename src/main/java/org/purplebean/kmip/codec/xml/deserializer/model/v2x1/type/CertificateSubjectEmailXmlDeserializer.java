package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectEmail;

public class CertificateSubjectEmailXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectEmail,
        CertificateSubjectEmail.CertificateSubjectEmailBuilder> {

  public CertificateSubjectEmailXmlDeserializer() {
    super(CertificateSubjectEmail.kmipTag, CertificateSubjectEmail.encodingType);
  }

  @Override
  protected CertificateSubjectEmail.CertificateSubjectEmailBuilder createBuilder() {
    return CertificateSubjectEmail.builder();
  }

  @Override
  protected void setValue(CertificateSubjectEmail.CertificateSubjectEmailBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectEmail build(
      CertificateSubjectEmail.CertificateSubjectEmailBuilder builder) {
    return builder.build();
  }
}