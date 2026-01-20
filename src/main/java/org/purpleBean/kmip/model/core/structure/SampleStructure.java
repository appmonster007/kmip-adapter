package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP SampleStructure structure.
 */
@Deprecated(since = "Sample structure object, not to be used for KMIP")
@Data
@Builder(toBuilder = true)
public class SampleStructure implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.P.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SampleStructure.class);
        }
    }

    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDate activationDate;
    private final State state;

    @Builder
    private SampleStructure(@NonNull ActivationDate activationDate, State state) {
        this.activationDate = activationDate;
        this.state = state;
        validate();
    }

    private void validate() {
        isSupported();
        List<KmipDataType> fields = new ArrayList<>();
        fields.add(activationDate);
        fields.add(state);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                );
            }
        }

        // Validate required fields
        // Add required-field checks as needed
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(activationDate, state)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
    }
}