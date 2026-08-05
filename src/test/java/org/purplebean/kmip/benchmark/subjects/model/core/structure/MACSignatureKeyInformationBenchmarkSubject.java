package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * Benchmark subject for {@link MACSignatureKeyInformation}.
 */
public class MACSignatureKeyInformationBenchmarkSubject
    extends KmipBenchmarkSubject<MACSignatureKeyInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link MACSignatureKeyInformationBenchmarkSubject}.
   */
  public MACSignatureKeyInformationBenchmarkSubject() throws Exception {
    MACSignatureKeyInformation subject = MACSignatureKeyInformation.of(
        UniqueIdentifier.of("fb44abe3-9721-43e0-a7d1-2568afe77d27"),
        CryptographicParameters
            .builder()
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build()
    );
    initialize(subject, MACSignatureKeyInformation.class);
  }

  @Override
  public String name() {
    return "MACSignatureKeyInformation";
  }

}