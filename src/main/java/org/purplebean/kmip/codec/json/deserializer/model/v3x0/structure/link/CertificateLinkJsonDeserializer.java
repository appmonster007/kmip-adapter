package org.purplebean.kmip.codec.json.deserializer.model.v3x0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.CertificateLink;

/**
 * JSON deserializer for {@link CertificateLink}.
 */
public class CertificateLinkJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateLink, CertificateLink.CertificateLinkBuilder> {

  /**
   * Constructs a new {@link CertificateLinkJsonDeserializer}.
   */
  public CertificateLinkJsonDeserializer() {
    super(CertificateLink.kmipTag, CertificateLink.encodingType);
  }

  @Override
  protected CertificateLink.CertificateLinkBuilder createBuilder() {
    return CertificateLink.builder();
  }

  @Override
  protected void setValue(CertificateLink.CertificateLinkBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateLink build(CertificateLink.CertificateLinkBuilder builder) {
    return builder.build();
  }
}
