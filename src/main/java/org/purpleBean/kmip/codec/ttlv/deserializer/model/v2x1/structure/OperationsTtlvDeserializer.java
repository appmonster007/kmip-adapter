package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2x1.structure.Operations;

public class OperationsTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Operations, Operations.OperationsBuilder> {

  public OperationsTtlvDeserializer() {
    super(Operations.kmipTag, Operations.encodingType);
  }

  @Override
  protected Operations.OperationsBuilder createBuilder() {
    return Operations.builder();
  }

  @Override
  protected void setValue(Operations.OperationsBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OPERATION -> builder.operation(mapper.readValue(p, Operation.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Operations build(Operations.OperationsBuilder builder) {
    return builder.build();
  }
}