package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ReKeyKeyPairOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_KEY_KEY_PAIR;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReKeyKeyPairOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, ReKeyKeyPairOpResponsePayload.class, ReKeyKeyPairOpResponsePayload::of);
        }
    }

    @NonNull
    private final PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier;
    @NonNull
    private final PublicKeyUniqueIdentifier publicKeyUniqueIdentifier;
    private final PrivateKeyTemplateAttribute privateKeyTemplateAttribute;
    private final PublicKeyTemplateAttribute publicKeyTemplateAttribute;

    @Builder
    private ReKeyKeyPairOpResponsePayload(
            @NonNull PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
            @NonNull PublicKeyUniqueIdentifier publicKeyUniqueIdentifier,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        this.privateKeyUniqueIdentifier = privateKeyUniqueIdentifier;
        this.publicKeyUniqueIdentifier = publicKeyUniqueIdentifier;
        this.privateKeyTemplateAttribute = privateKeyTemplateAttribute;
        this.publicKeyTemplateAttribute = publicKeyTemplateAttribute;
        validate();
    }

    public static ReKeyKeyPairOpResponsePayload of(
            @NonNull PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
            @NonNull PublicKeyUniqueIdentifier publicKeyUniqueIdentifier,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        return ReKeyKeyPairOpResponsePayload.builder()
                .privateKeyUniqueIdentifier(privateKeyUniqueIdentifier)
                .publicKeyUniqueIdentifier(publicKeyUniqueIdentifier)
                .privateKeyTemplateAttribute(privateKeyTemplateAttribute)
                .publicKeyTemplateAttribute(publicKeyTemplateAttribute)
                .build();
    }

    public static ReKeyKeyPairOpResponsePayload of(List<KmipDataType> values) {
        var builder = ReKeyKeyPairOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(PrivateKeyUniqueIdentifier.kmipTag)) {
            builder.privateKeyUniqueIdentifier((PrivateKeyUniqueIdentifier) map.get(PrivateKeyUniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(PublicKeyUniqueIdentifier.kmipTag)) {
            builder.publicKeyUniqueIdentifier((PublicKeyUniqueIdentifier) map.get(PublicKeyUniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(PrivateKeyTemplateAttribute.kmipTag)) {
            builder.privateKeyTemplateAttribute((PrivateKeyTemplateAttribute) map.get(PrivateKeyTemplateAttribute.kmipTag).getFirst());
        }
        if (map.containsKey(PublicKeyTemplateAttribute.kmipTag)) {
            builder.publicKeyTemplateAttribute((PublicKeyTemplateAttribute) map.get(KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        // Add validation logic here
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
        return supportedVersions.contains(spec) && getValue().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValue() {
        return Stream.of(
                        privateKeyUniqueIdentifier,
                        publicKeyUniqueIdentifier,
                        privateKeyTemplateAttribute,
                        publicKeyTemplateAttribute)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}