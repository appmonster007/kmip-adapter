package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SampleStructure implements KmipStructure {

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SECRET_DATA);
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    @NonNull
    private final ActivationDateAttribute activationDate;
    // @NonNull
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

    public static class SampleStructureBuilder {
        public SampleStructure build() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(activationDate);
            fields.add(state);

            // KMIP spec compatibility validation
            KmipSpec spec = KmipCodecContext.getSpec();
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                            String.format("Value '%s' is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            return new SampleStructure(activationDate, state);
        }
    }
}
