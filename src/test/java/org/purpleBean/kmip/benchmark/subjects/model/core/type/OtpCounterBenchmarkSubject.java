package org.purpleBean.kmip.benchmark.subjects.model.core.type;

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
import org.purpleBean.kmip.model.core.type.OtpCounter;

public class OtpCounterBenchmarkSubject extends KmipBenchmarkSubject<OtpCounter> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public OtpCounterBenchmarkSubject() throws Exception {
        OtpCounter subject = OtpCounter.of(1);  // TODO: Create a default instance
        initialize(subject, OtpCounter.class);
    }

    @Override
    public String name() {
        return "OtpCounter";
    }
}