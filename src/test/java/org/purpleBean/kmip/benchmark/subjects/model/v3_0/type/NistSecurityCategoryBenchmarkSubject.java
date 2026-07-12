package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;

public class NistSecurityCategoryBenchmarkSubject extends KmipBenchmarkSubject<NistSecurityCategory> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public NistSecurityCategoryBenchmarkSubject() throws Exception {
        NistSecurityCategory subject = NistSecurityCategory.of(123);
        initialize(subject, NistSecurityCategory.class);
    }

    @Override
    public String name() {
        return "NistSecurityCategory";
    }
}