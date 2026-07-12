package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2_1.enumeration.ProcessingStage;

public class AsynchronousRequestBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousRequest> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public AsynchronousRequestBenchmarkSubject() throws Exception {
        AsynchronousRequest subject = AsynchronousRequest.of(AsynchronousCorrelationValue.of(new byte[]{0x01}), Operation.Standard.CREATE.inst(), org.purpleBean.kmip.model.v2_1.type.SubmissionDate.of(java.time.OffsetDateTime.of(2024,1,1,0,0,0,0,java.time.ZoneOffset.UTC)), ProcessingStage.Standard.SUBMITTED.inst());
        initialize(subject, AsynchronousRequest.class);
    }

    @Override
    public String name() {
        return "AsynchronousRequest";
    }
}