package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CertificateIdentifier;
import org.purplebean.kmip.model.core.type.Issuer;
import org.purplebean.kmip.model.core.type.SerialNumber;

public class CertificateIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertificateIdentifier,
        CertificateIdentifier.CertificateIdentifierBuilder> {

  public CertificateIdentifierTtlvDeserializer() {
    super(CertificateIdentifier.kmipTag, CertificateIdentifier.encodingType);
  }

  @Override
  protected CertificateIdentifier.CertificateIdentifierBuilder createBuilder() {
    return CertificateIdentifier.builder();
  }

  @Override
  protected void setValue(CertificateIdentifier.CertificateIdentifierBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ISSUER -> builder.issuer(mapper.readValue(p, Issuer.class));
      case KmipTag.Standard.SERIAL_NUMBER ->
          builder.serialNumber(mapper.readValue(p, SerialNumber.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CertificateIdentifier build(
      CertificateIdentifier.CertificateIdentifierBuilder builder) {
    return builder.build();
  }
}