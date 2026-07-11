package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class Operations implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.OPERATIONS.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Operations.class);
        }
    }

    @NonNull
    @Singular
    private final List<Operation> operations;

    @Builder
    private Operations(List<Operation> operations) {
        this.operations = (operations == null) ? Collections.emptyList() : operations;
        validate();
    }

    public static Operations of(@NonNull List<Operation> operations) {
        return Operations.builder().operations(operations).build();
    }

    public static Operations of(@NonNull Operation... operations) {
        return Operations.builder().operations(List.of(operations)).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && operations.stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return operations.stream().map(KmipDataType.class::cast).toArray(KmipDataType[]::new);
    }
}
