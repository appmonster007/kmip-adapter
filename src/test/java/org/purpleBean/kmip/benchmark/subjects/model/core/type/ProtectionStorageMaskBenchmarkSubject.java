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
import org.purpleBean.kmip.model.core.type.ProtectionStorageMask;

public class ProtectionStorageMaskBenchmarkSubject extends KmipBenchmarkSubject<ProtectionStorageMask> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public ProtectionStorageMaskBenchmarkSubject() throws Exception {
        ProtectionStorageMask subject = ProtectionStorageMask.of(123);
        initialize(subject, ProtectionStorageMask.class);
    }

    @Override
    public String name() {
        return "ProtectionStorageMask";
    }
}