package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KMIP ApplicationSpecificInformation attribute structure.
 *
 * <p>Represents a ApplicationSpecificInformation in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class ApplicationSpecificInformation implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.APPLICATION_SPECIFIC_INFORMATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ApplicationSpecificInformation.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ApplicationSpecificInformation.class, ApplicationSpecificInformation::of);
        }
    }

    @NonNull
    private final ApplicationNamespace applicationNamespace;
    @NonNull
    private final ApplicationData applicationData;

    public static ApplicationSpecificInformation of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return ApplicationSpecificInformation.builder()
                .applicationNamespace((ApplicationNamespace) map.get(ApplicationNamespace.kmipTag).get(0))
                .applicationData((ApplicationData) map.get(ApplicationData.kmipTag).get(0))
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
        return List.of(applicationNamespace, applicationData);
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
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class ApplicationSpecificInformationBuilder {
        public ApplicationSpecificInformation build() {
            validate();
            return new ApplicationSpecificInformation(
                    applicationNamespace,
                    applicationData
            );
        }

        private void validate() {
            Objects.requireNonNull(applicationNamespace, "applicationNamespace cannot be null");
            Objects.requireNonNull(applicationData, "applicationData cannot be null");
        }
    }
}