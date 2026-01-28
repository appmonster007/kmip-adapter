package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class CreateSplitKeyOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.CREATE_SPLIT_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CreateSplitKeyOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, CreateSplitKeyOpRequestPayload.class, CreateSplitKeyOpRequestPayload::of);
        }
    }

    @NonNull
    private final ObjectType objectType;

    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final SplitKeyParts splitKeyParts;

    @NonNull
    private final SplitKeyThreshold splitKeyThreshold;

    @NonNull
    private final SplitKeyMethod splitKeyMethod;

    private final PrimeFieldSize primeFieldSize;

    @NonNull
    private final TemplateAttribute templateAttribute;

    @Builder
    private CreateSplitKeyOpRequestPayload(
            @NonNull ObjectType objectType,
            UniqueIdentifier uniqueIdentifier,
            @NonNull SplitKeyParts splitKeyParts,
            @NonNull SplitKeyThreshold splitKeyThreshold,
            @NonNull SplitKeyMethod splitKeyMethod,
            PrimeFieldSize primeFieldSize,
            @NonNull TemplateAttribute templateAttribute
    ) {
        this.objectType = objectType;
        this.uniqueIdentifier = uniqueIdentifier;
        this.splitKeyParts = splitKeyParts;
        this.splitKeyThreshold = splitKeyThreshold;
        this.splitKeyMethod = splitKeyMethod;
        this.primeFieldSize = primeFieldSize;
        this.templateAttribute = templateAttribute;
        validate();
    }

    public static CreateSplitKeyOpRequestPayload of(List<KmipDataType> values) {
        var builder = CreateSplitKeyOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKeyParts.kmipTag)) {
            builder.splitKeyParts((SplitKeyParts) map.get(SplitKeyParts.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKeyThreshold.kmipTag)) {
            builder.splitKeyThreshold((SplitKeyThreshold) map.get(SplitKeyThreshold.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKeyMethod.kmipTag)) {
            builder.splitKeyMethod((SplitKeyMethod) map.get(SplitKeyMethod.kmipTag).getFirst());
        }
        if (map.containsKey(PrimeFieldSize.kmipTag)) {
            builder.primeFieldSize((PrimeFieldSize) map.get(PrimeFieldSize.kmipTag).getFirst());
        }
        if (map.containsKey(TemplateAttribute.kmipTag)) {
            builder.templateAttribute((TemplateAttribute) map.get(TemplateAttribute.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
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
                        objectType,
                        uniqueIdentifier,
                        splitKeyParts,
                        splitKeyThreshold,
                        splitKeyMethod,
                        primeFieldSize,
                        templateAttribute)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
