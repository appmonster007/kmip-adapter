package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
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

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.LINK);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
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

    public static Link of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        var map = structure.getValue().stream().collect(Collectors.toMap(KmipDataType::getKmipTag, i -> i));
        return Link.builder()
                .linkType((LinkType) map.get(LinkType.kmipTag))
                .linkedObjectIdentifier((LinkedObjectIdentifier) map.get(LinkedObjectIdentifier.kmipTag))
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
                && linkType.isSupported()
                && linkedObjectIdentifier.isSupported();
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
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.Structure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(kmipTag.getDescription());
    }
}