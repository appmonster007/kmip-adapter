package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateRequestType,
        CertificateRequestType.CertificateRequestTypeBuilder> {

  public CertificateRequestTypeXmlDeserializer() {
    super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType);
  }

  @Override
  protected CertificateRequestType.CertificateRequestTypeBuilder createBuilder() {
    return CertificateRequestType.builder();
  }

  @Override
  protected void setValue(CertificateRequestType.CertificateRequestTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(CertificateRequestType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CertificateRequestType build(
      CertificateRequestType.CertificateRequestTypeBuilder builder) {
    return builder.build();
  }
}