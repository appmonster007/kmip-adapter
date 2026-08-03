package org.purplebean.kmip.model.v2x1.structure;

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
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purplebean.kmip.model.v2x1.type.RotateNameValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP {@code Rotate Name} attribute structure (KMIP v2.1+, tag {@code 0x42016F}).
 *
 * <p>Spec: {@code Rotate Name Value} (Text String, required) + {@code Rotate Name Type}
 * (Enumeration, required).
 *
 * <p>Client-initializable, client-modifiable, client-deletable, single-instance per §4.53.
 */
@Data
@Builder(toBuilder = true)
public class RotateName implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.ROTATE_NAME.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RotateName.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RotateName.class,
          RotateName::of);
    }
  }

  @NonNull
  private final RotateNameValue rotateNameValue;

  @NonNull
  private final RotateNameType rotateNameType;

  @Builder
  private RotateName(@NonNull RotateNameValue rotateNameValue,
                     @NonNull RotateNameType rotateNameType) {
    this.rotateNameValue = rotateNameValue;
    this.rotateNameType = rotateNameType;
    validate();
  }

  /**
   * Returns the {@link RotateName} instance wrapping the given value.
   */
  public static RotateName of(@NonNull RotateNameValue rotateNameValue,
                              @NonNull RotateNameType rotateNameType) {
    return new RotateName(rotateNameValue, rotateNameType);
  }

  /**
   * Returns the {@link RotateName} instance wrapping the given value.
   */
  public static RotateName of(@NonNull AttributeName attributeName,
                              @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure)
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return RotateName
        .builder()
        .rotateNameValue((RotateNameValue) map
            .get(RotateNameValue.kmipTag)
            .get(0))
        .rotateNameType((RotateNameType) map
            .get(RotateNameType.kmipTag)
            .get(0))
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    KmipSpec spec = KmipContext.getSpec();
    if (!rotateNameValue.isSupported()) {
      throw new IllegalArgumentException(
          String.format("RotateNameValue is not supported for KMIP spec %s", spec));
    }
    if (!rotateNameType.isSupported()) {
      throw new IllegalArgumentException(
          String.format("RotateNameType is not supported for KMIP spec %s", spec));
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
        .of(rotateNameValue, rotateNameType)
        .filter(Objects::nonNull)
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
    return false;
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
