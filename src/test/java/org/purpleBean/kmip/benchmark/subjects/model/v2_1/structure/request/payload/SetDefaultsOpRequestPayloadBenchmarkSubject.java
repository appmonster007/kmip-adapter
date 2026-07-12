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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetDefaultsOpRequestPayload;

public class SetDefaultsOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<SetDefaultsOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public SetDefaultsOpRequestPayloadBenchmarkSubject() throws Exception {
        SetDefaultsOpRequestPayload subject = SetDefaultsOpRequestPayload.builder().defaultsInformation(DefaultsInformation.of(java.util.List.of(ObjectDefaults.of(ObjectType.Standard.CERTIFICATE.inst(), Attributes.of(java.util.List.of()))))).build();
        initialize(subject, SetDefaultsOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "SetDefaultsOpRequestPayload";
    }
}