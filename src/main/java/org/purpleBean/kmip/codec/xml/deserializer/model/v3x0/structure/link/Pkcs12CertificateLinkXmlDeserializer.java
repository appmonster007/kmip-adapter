package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.Pkcs12CertificateLink;

public class Pkcs12CertificateLinkXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs12CertificateLink,
        Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder> {

  public Pkcs12CertificateLinkXmlDeserializer() {
    super(Pkcs12CertificateLink.kmipTag, Pkcs12CertificateLink.encodingType);
  }

  @Override
  protected Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder createBuilder() {
    return Pkcs12CertificateLink.builder();
  }

  @Override
  protected void setValue(Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Pkcs12CertificateLink build(
      Pkcs12CertificateLink.Pkcs12CertificateLinkBuilder builder) {
    return builder.build();
  }
}
