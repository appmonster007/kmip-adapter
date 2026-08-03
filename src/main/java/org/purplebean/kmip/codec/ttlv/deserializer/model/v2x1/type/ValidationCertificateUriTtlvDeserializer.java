package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;

/**
 * TTLV deserializer for {@link ValidationCertificateUri}.
 */
public class ValidationCertificateUriTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationCertificateUri,
        ValidationCertificateUri.ValidationCertificateUriBuilder> {

  /**
   * Constructs a new {@link ValidationCertificateUriTtlvDeserializer}.
   */
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