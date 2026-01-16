package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CredentialValueStructure;
import org.purpleBean.kmip.model.core.type.Username;

public class CredentialValueStructureBenchmarkSubject extends KmipBenchmarkSubject<CredentialValueStructure> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public CredentialValueStructureBenchmarkSubject() throws Exception {
        CredentialValueStructure subject = CredentialValueStructure.builder()
                .value(Username.of("test-value"))
                .build();
        initialize(subject, CredentialValueStructure.class);
    }

    @Override
    public String name() {
        return "CredentialValueStructure";
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