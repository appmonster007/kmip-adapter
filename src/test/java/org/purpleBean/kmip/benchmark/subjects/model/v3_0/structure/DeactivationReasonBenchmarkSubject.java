package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;

public class DeactivationReasonBenchmarkSubject extends KmipBenchmarkSubject<DeactivationReason> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public DeactivationReasonBenchmarkSubject() throws Exception {
        DeactivationReason subject = DeactivationReason.builder()
                .deactivationReasonCode(DeactivationReasonCode.of(DeactivationReasonCode.Standard.UNSPECIFIED))
                .build();
        initialize(subject, DeactivationReason.class);
    }

    @Override
    public String name() {
        return "DeactivationReason";
    }
}