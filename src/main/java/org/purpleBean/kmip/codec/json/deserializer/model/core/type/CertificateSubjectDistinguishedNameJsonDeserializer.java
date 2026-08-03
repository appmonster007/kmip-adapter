package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateSubjectDistinguishedName,
        CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder> {

  public CertificateSubjectDistinguishedNameJsonDeserializer() {
    super(CertificateSubjectDistinguishedName.kmipTag,
        CertificateSubjectDistinguishedName.encodingType);
  }

  @Override
  protected CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder createBuilder() {
    return CertificateSubjectDistinguishedName.builder();
  }

  @Override
  protected void setValue(
      CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder,
      String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateSubjectDistinguishedName build(
      CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
