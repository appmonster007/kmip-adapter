package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.RandomNumberGenerator;

public class RandomNumberGeneratorBenchmarkSubject extends KmipBenchmarkSubject<RandomNumberGenerator> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public RandomNumberGeneratorBenchmarkSubject() throws Exception {
        RandomNumberGenerator subject = RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
        initialize(subject, RandomNumberGenerator.class);
    }

    @Override
    public String name() {
        return "RandomNumberGenerator";
    }
}