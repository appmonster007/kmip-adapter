package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

public class AttestationAssertionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttestationAssertion,
        AttestationAssertion.AttestationAssertionBuilder> {

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