package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.MaskGeneratorHashingAlgorithm;

/**
 * TTLV deserializer for {@link MaskGeneratorHashingAlgorithm}.
 */
public class MaskGeneratorHashingAlgorithmTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MaskGeneratorHashingAlgorithm,
        MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder> {

  /**
   * Constructs a new {@link MaskGeneratorHashingAlgorithmTtlvDeserializer}.
   */
  public MaskGeneratorHashingAlgorithmTtlvDeserializer() {
    super(MaskGeneratorHashingAlgorithm.kmipTag, MaskGeneratorHashingAlgorithm.encodingType);
  }

  @Override
  protected MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder createBuilder() {
    return MaskGeneratorHashingAlgorithm.builder();
  }

  @Override
  protected void setValue(
      MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(MaskGeneratorHashingAlgorithm.fromValue(value));
  }

  @Override
  protected MaskGeneratorHashingAlgorithm build(
      MaskGeneratorHashingAlgorithm.MaskGeneratorHashingAlgorithmBuilder builder) {
    return builder.build();
  }
}
