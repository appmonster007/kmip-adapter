package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;

/**
 * XML deserializer for {@link CertificateSubjectDistinguishedName}.
 */
public class CertificateSubjectDistinguishedNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateSubjectDistinguishedName,
        CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link CertificateSubjectDistinguishedNameXmlDeserializer}.
   */
  public CertificateSubjectDistinguishedNameXmlDeserializer() {
    super(CertificateSubjectDistinguishedName.kmipTag,
        CertificateSubjectDistinguishedName.encodingType);
  }

  @Override
  protected CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder
      createBuilder() {
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