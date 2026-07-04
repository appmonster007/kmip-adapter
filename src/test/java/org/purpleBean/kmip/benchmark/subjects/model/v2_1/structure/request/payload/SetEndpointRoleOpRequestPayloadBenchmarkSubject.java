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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;

public class SetEndpointRoleOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<SetEndpointRoleOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public SetEndpointRoleOpRequestPayloadBenchmarkSubject() throws Exception {
        SetEndpointRoleOpRequestPayload subject = SetEndpointRoleOpRequestPayload.builder()
                .endpointRole(EndpointRole.Standard.CLIENT.inst())
                .build();
        initialize(subject, SetEndpointRoleOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "SetEndpointRoleOpRequestPayload";
    }
}