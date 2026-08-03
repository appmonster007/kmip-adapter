package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;

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