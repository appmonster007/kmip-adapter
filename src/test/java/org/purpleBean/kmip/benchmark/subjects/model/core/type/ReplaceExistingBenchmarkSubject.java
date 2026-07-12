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
import org.purpleBean.kmip.model.core.type.ReplaceExisting;

public class ReplaceExistingBenchmarkSubject extends KmipBenchmarkSubject<ReplaceExisting> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public ReplaceExistingBenchmarkSubject() throws Exception {
        ReplaceExisting subject = ReplaceExisting.of(true);
        initialize(subject, ReplaceExisting.class);
    }

    @Override
    public String name() {
        return "ReplaceExisting";
    }
}