package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;

/**
 * TTLV deserializer for {@link RevocationReasonCode}.
 */
public class RevocationReasonCodeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RevocationReasonCode,
        RevocationReasonCode.RevocationReasonCodeBuilder> {

  /**
   * Constructs a new {@link RevocationReasonCodeTtlvDeserializer}.
   */
  public RevocationReasonCodeTtlvDeserializer() {
    super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType);
  }

  @Override
  protected RevocationReasonCode.RevocationReasonCodeBuilder createBuilder() {
    return RevocationReasonCode.builder();
  }

  @Override
  protected void setValue(RevocationReasonCode.RevocationReasonCodeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(RevocationReasonCode.fromValue(value));
  }

  @Override
  protected RevocationReasonCode build(RevocationReasonCode.RevocationReasonCodeBuilder builder) {
    return builder.build();
  }
}
