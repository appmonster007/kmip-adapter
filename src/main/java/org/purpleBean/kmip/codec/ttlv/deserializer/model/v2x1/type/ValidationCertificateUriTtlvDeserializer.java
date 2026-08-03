package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.ValidationCertificateUri;

public class ValidationCertificateUriTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationCertificateUri,
        ValidationCertificateUri.ValidationCertificateUriBuilder> {

  public ValidationCertificateUriTtlvDeserializer() {
    super(ValidationCertificateUri.kmipTag, ValidationCertificateUri.encodingType);
  }

  @Override
  protected ValidationCertificateUri.ValidationCertificateUriBuilder createBuilder() {
    return ValidationCertificateUri.builder();
  }

  @Override
  protected void setValue(ValidationCertificateUri.ValidationCertificateUriBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationCertificateUri build(
      ValidationCertificateUri.ValidationCertificateUriBuilder builder) {
    return builder.build();
  }
}