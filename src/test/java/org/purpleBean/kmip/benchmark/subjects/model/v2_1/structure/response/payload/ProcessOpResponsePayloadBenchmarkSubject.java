package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;

public class ProcessOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<ProcessOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public ProcessOpResponsePayloadBenchmarkSubject() throws Exception {
        ProcessOpResponsePayload subject = ProcessOpResponsePayload.builder().asynchronousCorrelationValue(org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(new byte[]{0x01, 0x02})).build();
        initialize(subject, ProcessOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "ProcessOpResponsePayload";
    }
}