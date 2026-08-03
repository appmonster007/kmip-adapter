package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purplebean.kmip.model.core.type.Qlength;

public class CryptographicDomainParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CryptographicDomainParameters,
        CryptographicDomainParameters.CryptographicDomainParametersBuilder> {

  public CryptographicDomainParametersTtlvDeserializer() {
    super(CryptographicDomainParameters.kmipTag, CryptographicDomainParameters.encodingType);
  }

  @Override
  protected CryptographicDomainParameters.CryptographicDomainParametersBuilder createBuilder() {
    return CryptographicDomainParameters.builder();
  }

  @Override
  protected void setValue(
      CryptographicDomainParameters.CryptographicDomainParametersBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.QLENGTH -> builder.qlength(mapper.readValue(p, Qlength.class));
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(mapper.readValue(p, RecommendedCurve.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CryptographicDomainParameters build(
      CryptographicDomainParameters.CryptographicDomainParametersBuilder builder) {
    return builder.build();
  }
}