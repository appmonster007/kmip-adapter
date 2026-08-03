package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MACSignature;

/**
 * TTLV deserializer for {@link MACSignature}.
 */
public class MACSignatureTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<MACSignature, MACSignature.MACSignatureBuilder> {

  /**
   * Constructs a new {@link MACSignatureTtlvDeserializer}.
   */
  public MACSignatureTtlvDeserializer() {
    super(MACSignature.kmipTag, MACSignature.encodingType);
  }

  @Override
  protected MACSignature.MACSignatureBuilder createBuilder() {
    return MACSignature.builder();
  }

  @Override
  protected void setValue(MACSignature.MACSignatureBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected MACSignature build(MACSignature.MACSignatureBuilder builder) {
    return builder.build();
  }
}
