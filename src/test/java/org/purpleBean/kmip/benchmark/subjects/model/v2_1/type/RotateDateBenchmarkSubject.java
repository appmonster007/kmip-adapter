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
import org.purpleBean.kmip.model.v2_1.type.RotateDate;

public class RotateDateBenchmarkSubject extends KmipBenchmarkSubject<RotateDate> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public RotateDateBenchmarkSubject() throws Exception {
        RotateDate subject = RotateDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
        initialize(subject, RotateDate.class);
    }

    @Override
    public String name() {
        return "RotateDate";
    }
}