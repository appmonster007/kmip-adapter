package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateIdentifier;

public class ValidationCertificateIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationCertificateIdentifier,
        ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder> {

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