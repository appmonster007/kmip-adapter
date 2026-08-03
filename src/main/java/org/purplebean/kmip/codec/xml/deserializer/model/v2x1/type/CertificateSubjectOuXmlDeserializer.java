package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectOu;

public class CertificateSubjectOuXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectOu,
        CertificateSubjectOu.CertificateSubjectOuBuilder> {

  public CertificateSubjectOuXmlDeserializer() {
    super(CertificateSubjectOu.kmipTag, CertificateSubjectOu.encodingType);
  }

  @Override
  protected CertificateSubjectOu.CertificateSubjectOuBuilder createBuilder() {
    return CertificateSubjectOu.builder();
  }

  @Override
  protected void setValue(CertificateSubjectOu.CertificateSubjectOuBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectOu build(CertificateSubjectOu.CertificateSubjectOuBuilder builder) {
    return builder.build();
  }
}