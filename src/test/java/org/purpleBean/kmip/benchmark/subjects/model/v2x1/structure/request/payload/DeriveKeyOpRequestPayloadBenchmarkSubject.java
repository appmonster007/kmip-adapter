package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DeriveKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public DeriveKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    DeriveKeyOpRequestPayload subject = DeriveKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .derivationMethod(DerivationMethod.Standard.HASH.inst())
        .derivationParameters(DerivationParameters
            .builder()
            .cryptographicParameters(CryptographicParameters
                .builder()
                .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
                .build())
            .initializationVector(InitializationVector.of(new byte[] {0x01, 0x02}))
            .derivationData(DerivationData.of(new byte[] {0x03, 0x04}))
            .build())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, DeriveKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DeriveKeyOpRequestPayload";
  }
}