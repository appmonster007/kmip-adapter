package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;
import org.purpleBean.kmip.model.core.type.Username;

public class CredentialValueGenericStructureBenchmarkSubject extends KmipBenchmarkSubject<CredentialValueGenericStructure> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public CredentialValueGenericStructureBenchmarkSubject() throws Exception {
        CredentialValueGenericStructure subject = CredentialValueGenericStructure.builder()
                .value(Username.of("test-value"))
                .build();
        initialize(subject, CredentialValueGenericStructure.class);
    }

    @Override
    public String name() {
        return "CredentialValueGenericStructure";
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