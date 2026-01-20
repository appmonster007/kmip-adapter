package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class KeyWrappingData implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.KEY_WRAPPING_DATA.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyWrappingData.class);
        }
    }

    @NonNull
    private final WrappingMethod wrappingMethod;

    private final EncryptionKeyInformation encryptionKeyInformation;

    private final MACSignatureKeyInformation macSignatureKeyInformation;

    private final MACSignature macSignature;

    private final IVCounterNonce ivCounterNonce;

    private final EncodingOption encodingOption;

    @Builder
    private KeyWrappingData(
            @NonNull WrappingMethod wrappingMethod,
            EncryptionKeyInformation encryptionKeyInformation,
            MACSignatureKeyInformation macSignatureKeyInformation,
            MACSignature macSignature,
            IVCounterNonce ivCounterNonce,
            EncodingOption encodingOption
    ) {
        this.wrappingMethod = wrappingMethod;
        this.encryptionKeyInformation = encryptionKeyInformation;
        this.macSignatureKeyInformation = macSignatureKeyInformation;
        this.macSignature = macSignature;
        this.ivCounterNonce = ivCounterNonce;
        this.encodingOption = encodingOption;
        validate();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        wrappingMethod,
                        encryptionKeyInformation,
                        macSignatureKeyInformation,
                        macSignature,
                        ivCounterNonce,
                        encodingOption)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}