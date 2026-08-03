package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.PublicExponent;

public class TransparentRsaPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentRsaPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public TransparentRsaPublicKeyBenchmarkSubject() throws Exception {
    TransparentRsaPublicKey transparentRsaPublicKey = TransparentRsaPublicKey.of(
        Modulus.of(BigInteger.valueOf(1)),
        PublicExponent.of(BigInteger.valueOf(2))
    );
    initialize(transparentRsaPublicKey, TransparentRsaPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentRsaPublicKey";
  }

}