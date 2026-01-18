package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

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

}