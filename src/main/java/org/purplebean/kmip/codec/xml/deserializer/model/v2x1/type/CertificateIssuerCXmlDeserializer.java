package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerC;

/**
 * XML deserializer for {@link CertificateIssuerC}.
 */
public class CertificateIssuerCXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CertificateIssuerC,
        CertificateIssuerC.CertificateIssuerCBuilder> {

  /**
   * Constructs a new {@link CertificateIssuerCXmlDeserializer}.
   */
  public CertificateIssuerCXmlDeserializer() {
    super(CertificateIssuerC.kmipTag, CertificateIssuerC.encodingType);
  }

  @Override
  protected CertificateIssuerC.CertificateIssuerCBuilder createBuilder() {
    return CertificateIssuerC.builder();
  }

  @Override
  protected void setValue(CertificateIssuerC.CertificateIssuerCBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerC build(CertificateIssuerC.CertificateIssuerCBuilder builder) {
    return builder.build();
  }
}