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
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.model.v2_1.type.PredictionResistance;

public class RngParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RngParameters, RngParameters.RngParametersBuilder> {

  public RngParametersTtlvDeserializer() {
    super(RngParameters.kmipTag, RngParameters.encodingType);
  }

  @Override
  protected RngParameters.RngParametersBuilder createBuilder() {
    return RngParameters.builder();
  }

  @Override
  protected void setValue(RngParameters.RngParametersBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RNG_ALGORITHM ->
          builder.rngAlgorithm(mapper.readValue(p, RngAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
          builder.cryptographicLength(mapper.readValue(p, CryptographicLength.class));
      case KmipTag.Standard.HASHING_ALGORITHM ->
          builder.hashingAlgorithm(mapper.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.DRBG_ALGORITHM ->
          builder.drbgAlgorithm(mapper.readValue(p, DrbgAlgorithm.class));
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.FIPS186_VARIATION ->
          builder.fips186Variation(mapper.readValue(p, Fips186Variation.class));
      case KmipTag.Standard.PREDICTION_RESISTANCE ->
          builder.predictionResistance(mapper.readValue(p, PredictionResistance.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngParameters build(RngParameters.RngParametersBuilder builder) {
    return builder.build();
  }
}