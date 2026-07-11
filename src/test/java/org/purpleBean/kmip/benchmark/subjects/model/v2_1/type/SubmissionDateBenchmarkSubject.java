package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;

public class SubmissionDateBenchmarkSubject extends KmipBenchmarkSubject<SubmissionDate> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public SubmissionDateBenchmarkSubject() throws Exception {
        SubmissionDate subject = SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
        initialize(subject, SubmissionDate.class);
    }

    @Override
    public String name() {
        return "SubmissionDate";
    }
}