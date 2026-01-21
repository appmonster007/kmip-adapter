package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<DeriveKeyOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public DeriveKeyOpRequestPayloadBenchmarkSubject() throws Exception {
        DeriveKeyOpRequestPayload subject = DeriveKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .derivationMethod(DerivationMethod.Standard.HASH.inst())
                .derivationParameters(DerivationParameters.builder()
                        .cryptographicParameters(CryptographicParameters.builder()
                                .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
                                .build())
                        .initializationVector(InitializationVector.of(new byte[]{0x01, 0x02, 0x03}))
                        .derivationData(DerivationData.of(new byte[]{0x04, 0x05, 0x06}))
                        .build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
        initialize(subject, DeriveKeyOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "DeriveKeyOpRequestPayload";
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