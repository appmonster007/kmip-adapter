package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.v3_0.structure.link.WrappingKeyLink;

public class WrappingKeyLinkBenchmarkSubject extends KmipBenchmarkSubject<WrappingKeyLink> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public WrappingKeyLinkBenchmarkSubject() throws Exception {
        WrappingKeyLink subject = WrappingKeyLink.builder().build();  // TODO: Create a default instance
        initialize(subject, WrappingKeyLink.class);
    }

    @Override
    public String name() {
        return "WrappingKeyLink";
    }
}