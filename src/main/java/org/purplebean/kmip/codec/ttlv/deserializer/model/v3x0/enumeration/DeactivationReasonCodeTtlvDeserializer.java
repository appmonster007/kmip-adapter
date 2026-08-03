package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;

/**
 * TTLV deserializer for {@link DeactivationReasonCode}.
 */
public class DeactivationReasonCodeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivationReasonCode,
        DeactivationReasonCode.DeactivationReasonCodeBuilder> {

  /**
   * Constructs a new {@link DeactivationReasonCodeTtlvDeserializer}.
   */
  public DeactivationReasonCodeTtlvDeserializer() {
    super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType);
  }

  @Override
  protected DeactivationReasonCode.DeactivationReasonCodeBuilder createBuilder() {
    return DeactivationReasonCode.builder();
  }

  @Override
  protected void setValue(DeactivationReasonCode.DeactivationReasonCodeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(DeactivationReasonCode.fromValue(value));
  }

  @Override
  protected DeactivationReasonCode build(
      DeactivationReasonCode.DeactivationReasonCodeBuilder builder) {
    return builder.build();
  }
}
