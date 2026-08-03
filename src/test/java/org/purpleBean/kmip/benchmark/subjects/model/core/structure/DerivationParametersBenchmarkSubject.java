package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;

public class DerivationParametersBenchmarkSubject
    extends KmipBenchmarkSubject<DerivationParameters> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public DerivationParametersBenchmarkSubject() throws Exception {
    DerivationParameters subject = DerivationParameters
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
            .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
            .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .initializationVector(InitializationVector.of(new byte[] {0x01, 0x02, 0x03}))
        .derivationData(DerivationData.of(new byte[] {0x04, 0x05, 0x06}))
        .build();
    initialize(subject, DerivationParameters.class);
  }

  @Override
  public String name() {
    return "DerivationParameters";
  }

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}