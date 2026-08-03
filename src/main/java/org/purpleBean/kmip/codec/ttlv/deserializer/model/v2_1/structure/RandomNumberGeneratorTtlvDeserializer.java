package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.core.type.CryptographicLength;
import org.purpleBean.kmip.model.v2_1.structure.RandomNumberGenerator;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

public class RandomNumberGeneratorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RandomNumberGenerator,
        RandomNumberGenerator.RandomNumberGeneratorBuilder> {

  public RandomNumberGeneratorTtlvDeserializer() {
    super(RandomNumberGenerator.kmipTag, RandomNumberGenerator.encodingType);
  }

  @Override
  protected RandomNumberGenerator.RandomNumberGeneratorBuilder createBuilder() {
    return RandomNumberGenerator
        .builder()
        .rngParameters(RngParameters
            .builder()
            .build());
  }

  @Override
  protected void setValue(RandomNumberGenerator.RandomNumberGeneratorBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    RngParameters current = builder
        .build()
        .getRngParameters();
    RngParameters.RngParametersBuilder rngBuilder =
        current == null ? RngParameters.builder() : current.toBuilder();
    switch (nodeTag) {
      case KmipTag.Standard.RNG_PARAMETERS -> {
        builder.rngParameters(mapper.readValue(p, RngParameters.class));
        return;
      }
      case KmipTag.Standard.RNG_ALGORITHM ->
          rngBuilder.rngAlgorithm(mapper.readValue(p, RngAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          rngBuilder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
          rngBuilder.cryptographicLength(mapper.readValue(p, CryptographicLength.class));
      case KmipTag.Standard.HASHING_ALGORITHM ->
          rngBuilder.hashingAlgorithm(mapper.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.DRBG_ALGORITHM ->
          rngBuilder.drbgAlgorithm(mapper.readValue(p, DrbgAlgorithm.class));
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          rngBuilder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.FIPS186_VARIATION ->
          rngBuilder.fips186Variation(mapper.readValue(p, Fips186Variation.class));
      case KmipTag.Standard.PREDICTION_RESISTANCE -> rngBuilder.predictionResistance(
          mapper.readValue(p, org.purpleBean.kmip.model.v2_1.type.PredictionResistance.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
    builder.rngParameters(rngBuilder.build());
  }

  @Override
  protected RandomNumberGenerator build(
      RandomNumberGenerator.RandomNumberGeneratorBuilder builder) {
    return builder.build();
  }
}