package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectOu;

public class CertificateSubjectOuJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectOu,
        CertificateSubjectOu.CertificateSubjectOuBuilder> {

  public CertificateSubjectOuJsonDeserializer() {
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