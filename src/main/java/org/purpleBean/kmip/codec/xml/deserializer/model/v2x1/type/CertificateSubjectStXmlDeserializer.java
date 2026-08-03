package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectSt;

public class CertificateSubjectStXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectSt,
        CertificateSubjectSt.CertificateSubjectStBuilder> {

  public CertificateSubjectStXmlDeserializer() {
    super(CertificateSubjectSt.kmipTag, CertificateSubjectSt.encodingType);
  }

  @Override
  protected CertificateSubjectSt.CertificateSubjectStBuilder createBuilder() {
    return CertificateSubjectSt.builder();
  }

  @Override
  protected void setValue(CertificateSubjectSt.CertificateSubjectStBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectSt build(CertificateSubjectSt.CertificateSubjectStBuilder builder) {
    return builder.build();
  }
}