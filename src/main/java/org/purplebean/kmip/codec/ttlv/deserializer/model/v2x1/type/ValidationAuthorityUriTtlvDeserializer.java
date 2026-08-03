package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityUri;

/**
 * TTLV deserializer for {@link ValidationAuthorityUri}.
 */
public class ValidationAuthorityUriTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ValidationAuthorityUri,
        ValidationAuthorityUri.ValidationAuthorityUriBuilder> {

  /**
   * Constructs a new {@link ValidationAuthorityUriTtlvDeserializer}.
   */
  public ValidationAuthorityUriTtlvDeserializer() {
    super(ValidationAuthorityUri.kmipTag, ValidationAuthorityUri.encodingType);
  }

  @Override
  protected ValidationAuthorityUri.ValidationAuthorityUriBuilder createBuilder() {
    return ValidationAuthorityUri.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityUri.ValidationAuthorityUriBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ValidationAuthorityUri build(
      ValidationAuthorityUri.ValidationAuthorityUriBuilder builder) {
    return builder.build();
  }
}