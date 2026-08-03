package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.QuantumSafe;

public class QuantumSafeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<QuantumSafe, QuantumSafe.QuantumSafeBuilder> {

  public QuantumSafeTtlvDeserializer() {
    super(QuantumSafe.kmipTag, QuantumSafe.encodingType);
  }

  @Override
  protected QuantumSafe.QuantumSafeBuilder createBuilder() {
    return QuantumSafe.builder();
  }

  @Override
  protected void setValue(QuantumSafe.QuantumSafeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected QuantumSafe build(QuantumSafe.QuantumSafeBuilder builder) {
    return builder.build();
  }
}