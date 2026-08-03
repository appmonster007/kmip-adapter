package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.core.type.CryptographicLength;
import org.purplebean.kmip.model.v2x1.structure.RandomNumberGenerator;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;

public class RandomNumberGeneratorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RandomNumberGenerator,
        RandomNumberGenerator.RandomNumberGeneratorBuilder> {

  public RandomNumberGeneratorXmlDeserializer() {
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
    // KMIP §4.46: RandomNumberGenerator wraps the parameter fields directly (no <RngParameters>
    // XML tag).
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
      case KmipTag.Standard.PREDICTION_RESISTANCE ->
          rngBuilder.predictionResistance(ctxt.readValue(p, PredictionResistance.class));
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