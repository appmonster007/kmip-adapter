package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.model.core.type.IterationCount;
import org.purplebean.kmip.model.core.type.Salt;

/**
 * TTLV deserializer for {@link DerivationParameters}.
 */
public class DerivationParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DerivationParameters,
        DerivationParameters.DerivationParametersBuilder> {

  /**
   * Constructs a new {@link DerivationParametersTtlvDeserializer}.
   */
  public DerivationParametersTtlvDeserializer() {
    super(DerivationParameters.kmipTag, DerivationParameters.encodingType);
  }

  @Override
  protected DerivationParameters.DerivationParametersBuilder createBuilder() {
    return DerivationParameters.builder();
  }

  @Override
  protected void setValue(DerivationParameters.DerivationParametersBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
      case KmipTag.Standard.INITIALIZATION_VECTOR ->
          builder.initializationVector(mapper.readValue(p, InitializationVector.class));
      case KmipTag.Standard.DERIVATION_DATA ->
          builder.derivationData(mapper.readValue(p, DerivationData.class));
      case KmipTag.Standard.SALT -> builder.salt(mapper.readValue(p, Salt.class));
      case KmipTag.Standard.ITERATION_COUNT ->
          builder.iterationCount(mapper.readValue(p, IterationCount.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DerivationParameters build(DerivationParameters.DerivationParametersBuilder builder) {
    return builder.build();
  }
}