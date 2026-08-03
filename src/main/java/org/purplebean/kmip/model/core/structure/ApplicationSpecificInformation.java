package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.ApplicationData;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP ApplicationSpecificInformation attribute structure.
 *
 * <p>Represents a ApplicationSpecificInformation in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class ApplicationSpecificInformation implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.APPLICATION_SPECIFIC_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          ApplicationSpecificInformation.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType,
          ApplicationSpecificInformation.class, ApplicationSpecificInformation::of);
    }
  }

  @NonNull
  private final ApplicationNamespace applicationNamespace;
  @NonNull
  private final ApplicationData applicationData;

  @Builder
  private ApplicationSpecificInformation(@NonNull ApplicationNamespace applicationNamespace,
                                         @NonNull ApplicationData applicationData) {
    this.applicationNamespace = applicationNamespace;
    this.applicationData = applicationData;
    validate();
  }

  /**
   * Returns the {@link ApplicationSpecificInformation} instance wrapping the given value.
   */
  public static ApplicationSpecificInformation of(@NonNull AttributeName attributeName,
                                                  @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return ApplicationSpecificInformation
        .builder()
        .applicationNamespace((ApplicationNamespace) map
            .get(ApplicationNamespace.kmipTag)
            .get(0))
        .applicationData((ApplicationData) map
            .get(ApplicationData.kmipTag)
            .get(0))
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // No validation needed for this structure
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
  public KmipDataType[] getValue() {
    return Stream
        .of(applicationNamespace, applicationData)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
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
    return kmipTag.getDescription();
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofStructure(getValue());
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }
}
