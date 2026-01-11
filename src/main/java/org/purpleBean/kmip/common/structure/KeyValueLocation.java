package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KMIP KeyValueLocation attribute structure.
 *
 * <p>Represents a KeyValueLocation in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class KeyValueLocation implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_VALUE_LOCATION);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyValueLocation.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, KeyValueLocation.class, KeyValueLocation::of);
        }
    }

    @NonNull
    private final KeyValueLocationValue keyValueLocationValue;
    @NonNull
    private final KeyValueLocationType keyValueLocationType;

    public static KeyValueLocation of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return KeyValueLocation.builder()
                .keyValueLocationValue((KeyValueLocationValue) map.get(KeyValueLocationValue.kmipTag).get(0))
                .keyValueLocationType((KeyValueLocationType) map.get(KeyValueLocationType.kmipTag).get(0))
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
        return List.of(keyValueLocationValue, keyValueLocationType);
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
        return false;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return false;
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
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class KeyValueLocationBuilder {
        public KeyValueLocation build() {
            validate();
            return new KeyValueLocation(
                    keyValueLocationValue,
                    keyValueLocationType
            );
        }

        private void validate() {
            Objects.requireNonNull(keyValueLocationValue, "KeyValueLocationValue cannot be null");
            Objects.requireNonNull(keyValueLocationType, "KeyValueLocationType cannot be null");
        }
    }
}