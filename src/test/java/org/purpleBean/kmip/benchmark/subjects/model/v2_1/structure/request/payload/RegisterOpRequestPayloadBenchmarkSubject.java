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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RegisterOpRequestPayload;

public class RegisterOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<RegisterOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public RegisterOpRequestPayloadBenchmarkSubject() throws Exception {
        RegisterOpRequestPayload subject = RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .attributes(Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .object(SymmetricKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                                .build())
                        .build())
                .build();
        initialize(subject, RegisterOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "RegisterOpRequestPayload";
    }
}