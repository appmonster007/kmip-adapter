package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;

/**
 * JSON deserializer for {@link CertificateIssuerAlternativeName}.
 */
public class CertificateIssuerAlternativeNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIssuerAlternativeName,
        CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder> {

  /**
   * Constructs a new {@link CertificateIssuerAlternativeNameJsonDeserializer}.
   */
  public CertificateIssuerAlternativeNameJsonDeserializer() {
    super(CertificateIssuerAlternativeName.kmipTag, CertificateIssuerAlternativeName.encodingType);
  }

  @Override
  protected CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder
      createBuilder() {
    return CertificateIssuerAlternativeName.builder();
  }

  @Override
  protected void setValue(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected CertificateIssuerAlternativeName build(
      CertificateIssuerAlternativeName.CertificateIssuerAlternativeNameBuilder builder) {
    return builder.build();
  }
}
