package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;

public class OperationTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Operation, Operation.OperationBuilder> {

  public OperationTtlvDeserializer() {
    super(Operation.kmipTag, Operation.encodingType);
  }

  @Override
  protected Operation.OperationBuilder createBuilder() {
    return Operation.builder();
  }

  @Override
  protected void setValue(Operation.OperationBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(Operation.fromValue(value));
  }

  @Override
  protected Operation build(Operation.OperationBuilder builder) {
    return builder.build();
  }
}
