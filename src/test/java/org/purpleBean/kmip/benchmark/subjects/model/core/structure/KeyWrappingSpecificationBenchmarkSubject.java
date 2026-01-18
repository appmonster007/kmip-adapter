package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.UUID;

public class KeyWrappingSpecificationBenchmarkSubject extends KmipBenchmarkSubject<KeyWrappingSpecification> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public KeyWrappingSpecificationBenchmarkSubject() throws Exception {
        KeyWrappingSpecification subject = KeyWrappingSpecification.builder()
                .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
                .encryptionKeyInformation(EncryptionKeyInformation.builder()
                        .uniqueIdentifier(UniqueIdentifier.of(UUID.randomUUID().toString())).build())
                .build();
        initialize(subject, KeyWrappingSpecification.class);
    }

    @Override
    public String name() {
        return "KeyWrappingSpecification";
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