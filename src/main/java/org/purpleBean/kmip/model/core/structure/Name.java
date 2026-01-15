package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.NameValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Name attribute structure.
 *
 * <p>Represents a Name in KMIP.</p>
 *
 * <p>Attributes:
 * <ul>
 *   <li>Initially Set By: Client</li>
 *   <li>Modifiable By Client: Yes</li>
 *   <li>Deletable By Client: Yes</li>
 *   <li>Multiple Instances: Yes</li>
 *   <li>Applies To: All Managed Objects</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class Name implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.NAME.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Name.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Name.class, Name::of);
        }
    }

    @NonNull
    private final NameValue nameValue;

    @NonNull
    private final NameType nameType;

    public Name(@NonNull NameValue nameValue, @NonNull NameType nameType) {
        this.nameValue = Objects.requireNonNull(nameValue, "Name value cannot be null");
        this.nameType = Objects.requireNonNull(nameType, "Name type cannot be null");
    }

    public static Name of(@NonNull String name, @NonNull NameType type) {
        return new Name(NameValue.of(name), type);
    }

    public static Name of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return Name.builder()
                .nameValue((NameValue) map.get(NameValue.kmipTag).get(0))
                .nameType((NameType) map.get(NameType.kmipTag).get(0))
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
        return Stream.of(nameValue, nameType).filter(Objects::nonNull).toList();
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

    public static class NameBuilder {
        public Name build() {
            validate();
            return new Name(nameValue, nameType);
        }

        private void validate() {
            Objects.requireNonNull(nameValue, "Name value cannot be null");
            Objects.requireNonNull(nameType, "Name type cannot be null");

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            if (!nameValue.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("Name value is not supported for KMIP spec %s", spec)
                );
            }
            if (!nameType.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("Name type is not supported for KMIP spec %s", spec)
                );
            }
        }
    }
}