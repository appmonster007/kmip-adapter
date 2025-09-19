package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.ActivationDateAttribute;
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

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SECRET_DATA);
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDateAttribute activationDate;
    private final State state;

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

    /**
     * Builder for SampleStructure.
     */
    public static class SampleStructureBuilder {
        /**
         * Build a new SampleStructure instance with the current configuration.
         */
        public SampleStructure build() {
            // Validate required fields
            validate();

            // Create a copy to ensure immutability
            // SampleStructure result = new SampleStructure();
            // TODO: Copy all fields from instance to result
            // result.activationDate = this.activationDate;
            // result.state = this.state;

            return new SampleStructure(activationDate, state);
        }

        /**
         * Validate the current configuration.
         */
        private void validate() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(activationDate);
            fields.add(state);

            KmipSpec spec = KmipContext.getSpec();

            // Validate KMIP spec compatibility
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                            String.format("%s is not supported for KMIP spec %s",
                                    field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            // Validate required fields
            // if (activationDate == null) {
            //     throw new IllegalArgumentException("ActivationDate is required");
            // }
        }
    }
}
