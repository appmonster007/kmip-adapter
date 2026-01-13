package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierBenchmarkSubject extends KmipBenchmarkSubject<MediaIdentifier> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public MediaIdentifierBenchmarkSubject() throws Exception {
        MediaIdentifier mediaIdentifier = MediaIdentifier.builder().value("test-media-id").build();
        initialize(mediaIdentifier, MediaIdentifier.class);
    }

    @Override
    public String name() {
        return "MediaIdentifier";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}