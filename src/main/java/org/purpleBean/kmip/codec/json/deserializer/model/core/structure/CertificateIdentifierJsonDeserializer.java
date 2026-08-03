package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CertificateIdentifier;
import org.purplebean.kmip.model.core.type.Issuer;
import org.purplebean.kmip.model.core.type.SerialNumber;

public class CertificateIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CertificateIdentifier,
        CertificateIdentifier.CertificateIdentifierBuilder> {

  public CertificateIdentifierJsonDeserializer() {
    super(CertificateIdentifier.kmipTag, CertificateIdentifier.encodingType);
  }

  @Override
  protected CertificateIdentifier.CertificateIdentifierBuilder createBuilder() {
    return CertificateIdentifier.builder();
  }

  @Override
  protected void setValue(CertificateIdentifier.CertificateIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER -> builder.issuer(ctxt.readValue(p, Issuer.class));
      case KmipTag.Standard.SERIAL_NUMBER ->
          builder.serialNumber(ctxt.readValue(p, SerialNumber.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateIdentifier build(
      CertificateIdentifier.CertificateIdentifierBuilder builder) {
    return builder.build();
  }
}
