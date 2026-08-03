package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.AttestationType;

/**
 * TTLV deserializer for {@link AttestationType}.
 */
public class AttestationTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationType, AttestationType.AttestationTypeBuilder> {

  /**
   * Constructs a new {@link AttestationTypeTtlvDeserializer}.
   */
  public AttestationTypeTtlvDeserializer() {
    super(AttestationType.kmipTag, AttestationType.encodingType);
  }

  @Override
  protected AttestationType.AttestationTypeBuilder createBuilder() {
    return AttestationType.builder();
  }

  @Override
  protected void setValue(AttestationType.AttestationTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(AttestationType.fromValue(value));
  }

  @Override
  protected AttestationType build(AttestationType.AttestationTypeBuilder builder) {
    return builder.build();
  }
}
