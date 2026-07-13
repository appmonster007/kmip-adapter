package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DeriveKeyOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DERIVE_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeriveKeyOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, DeriveKeyOpRequestPayload.class, DeriveKeyOpRequestPayload::of);
        }
    }

    @NonNull
    private final ObjectType objectType;
    @Singular
    @NonNull
    private final List<UniqueIdentifier> uniqueIdentifiers;
    @NonNull
    private final DerivationMethod derivationMethod;
    @NonNull
    private final DerivationParameters derivationParameters;
    @NonNull
    private final Attributes attributes;

    @Builder
    private DeriveKeyOpRequestPayload(
            @NonNull ObjectType objectType,
            List<UniqueIdentifier> uniqueIdentifiers,
            @NonNull DerivationMethod derivationMethod,
            @NonNull DerivationParameters derivationParameters,
            @NonNull Attributes attributes
    ) {
        this.objectType = objectType;
        this.uniqueIdentifiers = (uniqueIdentifiers == null) ? Collections.emptyList() : uniqueIdentifiers;
        this.derivationMethod = derivationMethod;
        this.derivationParameters = derivationParameters;
        this.attributes = attributes;
        validate();
    }

    public static DeriveKeyOpRequestPayload of(List<KmipDataType> values) {
        var builder = DeriveKeyOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            map.get(UniqueIdentifier.kmipTag).forEach(item -> builder.uniqueIdentifier((UniqueIdentifier) item));
        }
        if (map.containsKey(DerivationMethod.kmipTag)) {
            builder.derivationMethod((DerivationMethod) map.get(DerivationMethod.kmipTag).getFirst());
        }
        if (map.containsKey(DerivationParameters.kmipTag)) {
            builder.derivationParameters((DerivationParameters) map.get(DerivationParameters.kmipTag).getFirst());
        }
        if (map.containsKey(Attributes.kmipTag)) {
            builder.attributes((Attributes) map.get(Attributes.kmipTag).getFirst());
        }
        return builder.build();
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(
                        objectType,
                        uniqueIdentifiers,
                        derivationMethod,
                        derivationParameters,
                        attributes)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
