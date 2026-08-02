package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<DeriveKeyOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

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
                        .initializationVector(InitializationVector.of(new byte[]{0x01, 0x02}))
                        .derivationData(DerivationData.of(new byte[]{0x03, 0x04}))
                        .build())
                .attributes(Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .build();
        initialize(subject, DeriveKeyOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "DeriveKeyOpRequestPayload";
    }
}