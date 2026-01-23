package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class PutOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.PUT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PutOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, PutOpRequestPayload.class, PutOpRequestPayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final PutFunction putFunction;

    private final ReplacedUniqueIdentifier replacedUniqueIdentifier;

    @NonNull
    private final ManagedObject managedObject;

    @Singular
    private final List<Attribute> attributes;

    @Builder
    private PutOpRequestPayload(
            @NonNull UniqueIdentifier uniqueIdentifier,
            @NonNull PutFunction putFunction,
            ReplacedUniqueIdentifier replacedUniqueIdentifier,
            @NonNull ManagedObject managedObject,
            List<Attribute> attributes
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.putFunction = putFunction;
        this.replacedUniqueIdentifier = replacedUniqueIdentifier;
        this.managedObject = managedObject;
        this.attributes = attributes;
        validate();
    }

    public static PutOpRequestPayload of(List<KmipDataType> values) {
        var builder = PutOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(PutFunction.kmipTag)) {
            builder.putFunction((PutFunction) map.get(PutFunction.kmipTag).getFirst());
        }
        if (map.containsKey(ReplacedUniqueIdentifier.kmipTag)) {
            builder.replacedUniqueIdentifier((ReplacedUniqueIdentifier) map.get(ReplacedUniqueIdentifier.kmipTag).getFirst());
        }

        if (map.containsKey(Certificate.kmipTag)) {
            builder.managedObject((Certificate) map.get(Certificate.kmipTag).getFirst());
        } else if (map.containsKey(SymmetricKey.kmipTag)) {
            builder.managedObject((SymmetricKey) map.get(SymmetricKey.kmipTag).getFirst());
        } else if (map.containsKey(PrivateKey.kmipTag)) {
            builder.managedObject((PrivateKey) map.get(PrivateKey.kmipTag).getFirst());
        } else if (map.containsKey(PublicKey.kmipTag)) {
            builder.managedObject((PublicKey) map.get(PublicKey.kmipTag).getFirst());
        } else if (map.containsKey(SplitKey.kmipTag)) {
            builder.managedObject((SplitKey) map.get(SplitKey.kmipTag).getFirst());
        } else if (map.containsKey(Template.kmipTag)) {
            builder.managedObject((Template) map.get(Template.kmipTag).getFirst());
        } else if (map.containsKey(SecretData.kmipTag)) {
            builder.managedObject((SecretData) map.get(SecretData.kmipTag).getFirst());
        } else if (map.containsKey(OpaqueObject.kmipTag)) {
            builder.managedObject((OpaqueObject) map.get(OpaqueObject.kmipTag).getFirst());
        }

        if (map.containsKey(Attribute.kmipTag)) {
            map.get(Attribute.kmipTag).forEach(item -> builder.attribute((Attribute) item));
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        uniqueIdentifier,
                        putFunction,
                        replacedUniqueIdentifier,
                        managedObject,
                        attributes)
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
