package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.core.type.CryptographicLength;
import org.purpleBean.kmip.model.v2x1.structure.RandomNumberGenerator;
import org.purpleBean.kmip.model.v2x1.structure.RngParameters;

public class RandomNumberGeneratorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RandomNumberGenerator,
        RandomNumberGenerator.RandomNumberGeneratorBuilder> {

  public RandomNumberGeneratorJsonDeserializer() {
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
  protected void setValue(RandomNumberGenerator.RandomNumberGeneratorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    RngParameters current = builder
        .build()
        .getRngParameters();
    RngParameters.RngParametersBuilder rngBuilder =
        current == null ? RngParameters.builder() : current.toBuilder();
    switch (nodeTag) {
      case KmipTag.Standard.RNG_PARAMETERS -> {
        builder.rngParameters(ctxt.readValue(p, RngParameters.class));
        return;
      }
      case KmipTag.Standard.RNG_ALGORITHM ->
          rngBuilder.rngAlgorithm(ctxt.readValue(p, RngAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          rngBuilder.cryptographicAlgorithm(ctxt.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
          rngBuilder.cryptographicLength(ctxt.readValue(p, CryptographicLength.class));
      case KmipTag.Standard.HASHING_ALGORITHM ->
          rngBuilder.hashingAlgorithm(ctxt.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.DRBG_ALGORITHM ->
          rngBuilder.drbgAlgorithm(ctxt.readValue(p, DrbgAlgorithm.class));
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          rngBuilder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.FIPS186_VARIATION ->
          rngBuilder.fips186Variation(ctxt.readValue(p, Fips186Variation.class));
      case KmipTag.Standard.PREDICTION_RESISTANCE -> rngBuilder.predictionResistance(
          ctxt.readValue(p, org.purpleBean.kmip.model.v2x1.type.PredictionResistance.class));
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