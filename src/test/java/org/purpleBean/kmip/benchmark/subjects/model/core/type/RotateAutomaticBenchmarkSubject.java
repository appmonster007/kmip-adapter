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
import org.purpleBean.kmip.model.core.type.RotateAutomatic;

public class RotateAutomaticBenchmarkSubject extends KmipBenchmarkSubject<RotateAutomatic> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public RotateAutomaticBenchmarkSubject() throws Exception {
        RotateAutomatic subject = RotateAutomatic.of(true);
        initialize(subject, RotateAutomatic.class);
    }

    @Override
    public String name() {
        return "RotateAutomatic";
    }
}