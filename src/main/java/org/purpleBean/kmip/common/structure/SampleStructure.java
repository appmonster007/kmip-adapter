package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * KMIP SampleStructure structure.
 */
@Data
@Builder
public class SampleStructure implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.P);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDate activationDate;
    private final State state;

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
        List<KmipDataType> values = new ArrayList<>();
        values.add(activationDate);
        values.add(state);
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    public static class SampleStructureBuilder {
        public SampleStructure build() {
            // Validate required fields
            validate();
            return new SampleStructure(activationDate, state);
        }

        private void validate() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(activationDate);
            fields.add(state);

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                            String.format("%s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            // Validate required fields
            // Add required-field checks as needed
        }
    }
}
