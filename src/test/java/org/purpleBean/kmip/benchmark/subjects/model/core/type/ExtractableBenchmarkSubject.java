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
import org.purpleBean.kmip.model.core.type.Extractable;

public class ExtractableBenchmarkSubject extends KmipBenchmarkSubject<Extractable> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public ExtractableBenchmarkSubject() throws Exception {
        Extractable subject = Extractable.of(true);
        initialize(subject, Extractable.class);
    }

    @Override
    public String name() {
        return "Extractable";
    }
}