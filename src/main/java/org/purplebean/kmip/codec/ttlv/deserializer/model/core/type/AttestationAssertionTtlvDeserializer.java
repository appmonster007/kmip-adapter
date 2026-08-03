package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AttestationAssertion;

/**
 * TTLV deserializer for {@link AttestationAssertion}.
 */
public class AttestationAssertionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationAssertion,
        AttestationAssertion.AttestationAssertionBuilder> {

  /**
   * Constructs a new {@link AttestationAssertionTtlvDeserializer}.
   */
  public AttestationAssertionTtlvDeserializer() {
    super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType);
  }

  @Override
  protected AttestationAssertion.AttestationAssertionBuilder createBuilder() {
    return AttestationAssertion.builder();
  }

  @Override
  protected void setValue(AttestationAssertion.AttestationAssertionBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected AttestationAssertion build(AttestationAssertion.AttestationAssertionBuilder builder) {
    return builder.build();
  }
}