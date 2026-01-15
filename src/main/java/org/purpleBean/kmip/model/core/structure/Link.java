package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.model.core.enumeration.LinkType;
import org.purpleBean.kmip.model.core.enumeration.State;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KMIP Link attribute structure.
 *
 * <p>Represents a Link in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class Link implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.LINK.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Link.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Link.class, Link::of);
        }
    }

    @NonNull
    private final LinkType linkType;
    @NonNull
    private final LinkedObjectIdentifier linkedObjectIdentifier;

    public static Link of(@NonNull LinkType linkType, @NonNull LinkedObjectIdentifier linkedObjectIdentifier) {
        return Link.builder()
                .linkType(linkType)
                .linkedObjectIdentifier(linkedObjectIdentifier)
                .build();
    }

    public static Link of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return Link.builder()
                .linkType((LinkType) map.get(LinkType.kmipTag).get(0))
                .linkedObjectIdentifier((LinkedObjectIdentifier) map.get(LinkedObjectIdentifier.kmipTag).get(0))
                .build();
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
        return List.of(linkType, linkedObjectIdentifier);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public boolean isAlwaysPresent() {
        return false;
    }

    @Override
    public boolean isServerInitializable() {
        return true;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return true;
    }

    @Override
    public boolean isClientModifiable(State state) {
        return true;
    }

    @Override
    public boolean isClientDeletable() {
        return true;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return true;
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueStructure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(kmipTag.getDescription());
    }

    public static class LinkBuilder {
        public Link build() {
            validate();
            return new Link(
                    linkType,
                    linkedObjectIdentifier
            );
        }

        private void validate() {
            Objects.requireNonNull(linkType, "LinkType cannot be null");
            Objects.requireNonNull(linkedObjectIdentifier, "LinkedObjectIdentifier cannot be null");
        }
    }
}