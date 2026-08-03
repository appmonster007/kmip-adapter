package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.OperationPolicyName;

/**
 * TTLV deserializer for {@link OperationPolicyName}.
 */
public class OperationPolicyNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OperationPolicyName,
        OperationPolicyName.OperationPolicyNameBuilder> {

  /**
   * Constructs a new {@link OperationPolicyNameTtlvDeserializer}.
   */
  public OperationPolicyNameTtlvDeserializer() {
    super(OperationPolicyName.kmipTag, OperationPolicyName.encodingType);
  }

  @Override
  protected OperationPolicyName.OperationPolicyNameBuilder createBuilder() {
    return OperationPolicyName.builder();
  }

  @Override
  protected void setValue(OperationPolicyName.OperationPolicyNameBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected OperationPolicyName build(OperationPolicyName.OperationPolicyNameBuilder builder) {
    return builder.build();
  }
}