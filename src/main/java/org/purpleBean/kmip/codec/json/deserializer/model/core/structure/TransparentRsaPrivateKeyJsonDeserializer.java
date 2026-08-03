package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purplebean.kmip.model.core.type.CRTCoefficient;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.PrimeExponentP;
import org.purplebean.kmip.model.core.type.PrimeExponentQ;
import org.purplebean.kmip.model.core.type.PrivateExponent;
import org.purplebean.kmip.model.core.type.PublicExponent;
import org.purplebean.kmip.model.core.type.Q;

public class TransparentRsaPrivateKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentRsaPrivateKey,
        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder> {

  public TransparentRsaPrivateKeyJsonDeserializer() {
    super(TransparentRsaPrivateKey.kmipTag, TransparentRsaPrivateKey.encodingType);
  }

  @Override
  protected TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder createBuilder() {
    return TransparentRsaPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MODULUS -> builder.modulus(ctxt.readValue(p, Modulus.class));
      case KmipTag.Standard.PRIVATE_EXPONENT ->
          builder.privateExponent(ctxt.readValue(p, PrivateExponent.class));
      case KmipTag.Standard.PUBLIC_EXPONENT ->
          builder.publicExponent(ctxt.readValue(p, PublicExponent.class));
      case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
      case KmipTag.Standard.PRIME_EXPONENT_P ->
          builder.primeExponentP(ctxt.readValue(p, PrimeExponentP.class));
      case KmipTag.Standard.PRIME_EXPONENT_Q ->
          builder.primeExponentQ(ctxt.readValue(p, PrimeExponentQ.class));
      case KmipTag.Standard.CRT_COEFFICIENT ->
          builder.crtCoefficient(ctxt.readValue(p, CRTCoefficient.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentRsaPrivateKey build(
      TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder) {
    return builder.build();
  }
}