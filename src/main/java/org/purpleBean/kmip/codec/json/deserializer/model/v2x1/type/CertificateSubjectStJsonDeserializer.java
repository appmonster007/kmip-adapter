package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectSt;

public class CertificateSubjectStJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectSt,
        CertificateSubjectSt.CertificateSubjectStBuilder> {

  public CertificateSubjectStJsonDeserializer() {
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