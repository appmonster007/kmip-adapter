package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateType, CertificateType.CertificateTypeBuilder> {

  public CertificateTypeXmlDeserializer() {
    super(CertificateType.kmipTag, CertificateType.encodingType);
  }

  @Override
  protected CertificateType.CertificateTypeBuilder createBuilder() {
    return CertificateType.builder();
  }

  @Override
  protected void setValue(CertificateType.CertificateTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(CertificateType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CertificateType build(CertificateType.CertificateTypeBuilder builder) {
    return builder.build();
  }
}