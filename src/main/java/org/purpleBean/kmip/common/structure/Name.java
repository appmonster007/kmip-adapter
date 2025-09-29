package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Name attribute.
 *
 * <p>Represents a human-readable name for identifying Managed Objects in KMIP.</p>
 *
 * <p>According to KMIP v1.2 Section 3.2, a Name is a structure containing:
 * <ul>
 *   <li>Name Value (Text String) - The actual name value</li>
 *   <li>Name Type (Enumeration) - The type/format of the name</li>
 * </ul>
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
@Builder
public class Name implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NAME);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> SUPPORTED_VERSIONS = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : SUPPORTED_VERSIONS) {
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
        NameBuilder nameBuilder = Name.builder();
        if (attributeValue.getEncodingType() != EncodingType.STRUCTURE) {
            throw new IllegalArgumentException("Invalid encoding type");
        }
        List<KmipDataType> fields = (List<KmipDataType>) attributeValue.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof NameValue nameValue) {
                nameBuilder.nameValue(nameValue);
            }
            if (field instanceof NameType nameType) {
                nameBuilder.nameType(nameType);
            }
        }
        return nameBuilder.build();
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
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return SUPPORTED_VERSIONS.contains(spec)
                && nameValue.isSupportedFor(spec)
                && nameType.isSupportedFor(spec);
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
        return true; // Client can initialize name
    }

    @Override
    public boolean isServerModifiable(State state) {
        return false; // Only client can modify names
    }

    @Override
    public boolean isClientModifiable(State state) {
        return true; // Client can modify names
    }

    @Override
    public boolean isClientDeletable() {
        return true; // Client can delete names
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return true; // Multiple names are allowed
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of("Name");
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
            if (!nameValue.isSupportedFor(spec)) {
                throw new IllegalArgumentException(
                        String.format("Name value is not supported for KMIP spec %s", spec)
                );
            }
            if (!nameType.isSupportedFor(spec)) {
                throw new IllegalArgumentException(
                        String.format("Name type is not supported for KMIP spec %s", spec)
                );
            }
        }
    }
}
