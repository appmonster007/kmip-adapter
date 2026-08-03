package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;

/**
 * TTLV deserializer for {@link ValidationCertificateIdentifier}.
 */
public class ValidationCertificateIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationCertificateIdentifier,
        ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder> {

  /**
   * Constructs a new {@link ValidationCertificateIdentifierTtlvDeserializer}.
   */
  public ValidationCertificateIdentifierTtlvDeserializer() {
    super(ValidationCertificateIdentifier.kmipTag, ValidationCertificateIdentifier.encodingType);
  }

  @Override
  protected ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder createBuilder() {
    return ValidationCertificateIdentifier.builder();
  }

  @Override
  protected void setValue(
      ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationCertificateIdentifier build(
      ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder builder) {
    return builder.build();
  }
}