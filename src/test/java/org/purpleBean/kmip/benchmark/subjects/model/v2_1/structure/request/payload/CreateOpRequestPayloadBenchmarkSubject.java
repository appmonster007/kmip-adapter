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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateOpRequestPayload;

public class CreateOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<CreateOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public CreateOpRequestPayloadBenchmarkSubject() throws Exception {
        CreateOpRequestPayload subject = CreateOpRequestPayload.builder().objectType(ObjectType.Standard.SYMMETRIC_KEY.inst()).attributes(org.purpleBean.kmip.model.v2_1.structure.Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build()).build();
        initialize(subject, CreateOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "CreateOpRequestPayload";
    }
}