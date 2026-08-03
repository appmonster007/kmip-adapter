package org.purpleBean.kmip.model.core.structure;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
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
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class CustomAttribute implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class,
          CustomAttribute::of);
    }
  }

  @NonNull
  private final AttributeName attributeName;
  @NonNull
  private final AttributeValue attributeValue;

  @Builder
  private CustomAttribute(@NonNull AttributeName attributeName,
                          @NonNull AttributeValue attributeValue) {
    this.attributeName = attributeName;
    this.attributeValue = attributeValue;
    validate();
  }

  public static CustomAttribute of(@NonNull AttributeName attributeName,
                                   @NonNull AttributeValue attributeValue) {
    return new CustomAttribute(attributeName, attributeValue);
  }

  public static CustomAttribute of(@NonNull String name, @NonNull AttributeValue value) {
    return of(AttributeName.of(name), value);
  }

  public static CustomAttribute of(@NonNull AttributeName name, @NonNull KmipDataType... values) {
    if (Stream
        .of(values)
        .anyMatch(v -> v.getEncodingType() == EncodingType.STRUCTURE)) {
      throw new IllegalArgumentException("Custom attribute cannot contain sub-structure");
    }
    return of(name, AttributeValue.ofStructure(values));
  }

  public static CustomAttribute of(@NonNull String name, @NonNull AttributeValue... values) {
    return of(AttributeName.of(name), AttributeValue.ofStructure(values));
  }

  public static boolean isValidCustomAttributeName(@NonNull String name) {
    return isCustomServerAttribute(name) || isCustomClientAttribute(name);
  }

  public static boolean isValidCustomAttributeName(@NonNull AttributeName name) {
    return isValidCustomAttributeName(name.getValue());
  }

  private static boolean isValidCustomAttributeValue(@NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() == EncodingType.STRUCTURE) {
      return (attributeValue.getValue() instanceof KmipDataType[] structure)
          && Stream
          .of(structure)
          .noneMatch(value -> value instanceof KmipStructure);
    }
    return true;
  }

  public static boolean isCustomServerAttribute(@NonNull String name) {
    Pattern pattern = Pattern.compile("^y-.*?", Pattern.CASE_INSENSITIVE);
    return pattern
        .matcher(name)
        .matches();
  }

  public static boolean isCustomClientAttribute(@NonNull String name) {
    Pattern pattern = Pattern.compile("^x-.*?", Pattern.CASE_INSENSITIVE);
    return pattern
        .matcher(name)
        .matches();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (!isValidCustomAttributeName(attributeName)) {
      throw new IllegalArgumentException("Custom attribute name is invalid");
    }
    if (!isValidCustomAttributeValue(attributeValue)) {
      throw new IllegalArgumentException("Custom attribute value is invalid");
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(attributeName, attributeValue)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
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
    return CustomAttribute.isCustomServerAttribute(attributeName.getValue());
  }

  @Override
  public boolean isClientModifiable(State state) {
    return CustomAttribute.isCustomClientAttribute(attributeName.getValue());
  }

  @Override
  public boolean isClientDeletable() {
    return CustomAttribute.isCustomClientAttribute(attributeName.getValue());
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return true;
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }
}
