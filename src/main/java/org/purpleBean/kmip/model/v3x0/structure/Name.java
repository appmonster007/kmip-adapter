package org.purpleBean.kmip.model.v3x0.structure;

import java.util.Set;
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
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.util.StringUtils;

/**
 * KMIP v3.0 Name attribute structure. NameType was reserved in v3.0, so only NameValue is present.
 */
@Data
@Builder(toBuilder = true)
public class Name implements KmipStructure, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.NAME.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

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

  @Builder
  private Name(@NonNull NameValue nameValue) {
    this.nameValue = nameValue;
    validate();
  }

  public static Name of(@NonNull String name) {
    return new Name(NameValue.of(name));
  }

  public static Name of(@NonNull NameValue nameValue) {
    return new Name(nameValue);
  }

  public static Name of(@NonNull AttributeName attributeName,
                        @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    for (KmipDataType component : structure) {
      if (component instanceof NameValue nv) {
        return new Name(nv);
      }
    }
    throw new IllegalArgumentException("NameValue not found in attribute value");
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (!nameValue.isSupported()) {
      throw new IllegalArgumentException(
          String.format("Name value is not supported for KMIP spec %s", KmipContext.getSpec())
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
    return new KmipDataType[] {nameValue};
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
