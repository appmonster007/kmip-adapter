package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purplebean.kmip.model.core.type.CRTCoefficient;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.PrimeExponentP;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;
import org.purplebean.kmip.model.core.type.PrivateExponent;
import org.purplebean.kmip.model.core.type.PublicExponent;
import org.purplebean.kmip.model.core.type.Q;

public class TransparentRsaPrivateKeyTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TransparentRsaPrivateKey,
        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder> {

  public TransparentRsaPrivateKeyTtlvDeserializer() {
    super(TransparentRsaPrivateKey.kmipTag, TransparentRsaPrivateKey.encodingType);
  }

  @Override
  protected TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder createBuilder() {
    return TransparentRsaPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MODULUS -> builder.modulus(mapper.readValue(p, Modulus.class));
      case KmipTag.Standard.PRIVATE_EXPONENT ->
          builder.privateExponent(mapper.readValue(p, PrivateExponent.class));
      case KmipTag.Standard.PUBLIC_EXPONENT ->
          builder.publicExponent(mapper.readValue(p, PublicExponent.class));
      case KmipTag.Standard.P -> builder.p(mapper.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(mapper.readValue(p, Q.class));
      case KmipTag.Standard.PRIME_EXPONENT_P ->
          builder.primeExponentP(mapper.readValue(p, PrimeExponentP.class));
      case KmipTag.Standard.PRIME_EXPONENT_Q ->
          builder.primeExponentQ(mapper.readValue(p, PrimeExponentQ.class));
      case KmipTag.Standard.CRT_COEFFICIENT ->
          builder.crtCoefficient(mapper.readValue(p, CRTCoefficient.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentRsaPrivateKey build(
      TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder) {
    return builder.build();
  }
}