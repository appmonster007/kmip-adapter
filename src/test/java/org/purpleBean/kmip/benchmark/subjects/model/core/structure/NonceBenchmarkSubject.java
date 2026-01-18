package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;

public class NonceBenchmarkSubject extends KmipBenchmarkSubject<Nonce> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public NonceBenchmarkSubject() throws Exception {
        Nonce subject = Nonce.builder()
                .nonceId(NonceId.of("test-id".getBytes()))
                .nonceValue(NonceValue.of(new byte[8]))
                .build();
        initialize(subject, Nonce.class);
    }

    @Override
    public String name() {
        return "Nonce";
    }

}