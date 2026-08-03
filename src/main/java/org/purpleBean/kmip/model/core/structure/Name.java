package org.purpleBean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.util.StringUtils;

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
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Name.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Name.class, Name::of);
    }
  }

  @NonNull
  private final NameValue nameValue;

  @NonNull
  private final NameType nameType;

  @Builder
  private Name(@NonNull NameValue nameValue, @NonNull NameType nameType) {
    this.nameValue = nameValue;
    this.nameType = nameType;
    validate();
  }

  public static Name of(@NonNull String name, @NonNull NameType type) {
    return new Name(NameValue.of(name), type);
  }

  public static Name of(@NonNull NameValue nameValue, @NonNull NameType nameType) {
    return new Name(nameValue, nameType);
  }

  public static Name of(@NonNull AttributeName attributeName,
                        @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return Name
        .builder()
        .nameValue((NameValue) map
            .get(NameValue.kmipTag)
            .get(0))
        .nameType((NameType) map
            .get(NameType.kmipTag)
            .get(0))
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
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
        .of(nameValue, nameType)
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
