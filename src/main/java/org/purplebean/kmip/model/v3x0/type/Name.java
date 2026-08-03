package org.purplebean.kmip.model.v3x0.type;

import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP Name datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class Name implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.NAME.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);
  // introduced in KMIP 3.0 — extend if present in later specs

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
  private final String value;

  @Builder
  private Name(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link Name} instance wrapping the given value.
   */
  public static Name of(@NonNull String value) {
    return new Name(value);
  }

  /**
   * Returns the {@link Name} instance wrapping the given value.
   */
  public static Name of(@NonNull AttributeName attributeName,
                        @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType
        || !(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return new Name(value);
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // No validation needed for this structure
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofTextString(value);
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
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
    return supportedVersions.contains(spec);
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
  public boolean isServerModifiable(@NonNull State state) {
    return false;
  }

  @Override
  public boolean isClientModifiable(@NonNull State state) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name that = (Name) o;
    // TODO: Adjust equals for specific data type if needed (e.g., OffsetDateTime.withNano(0)
    //  .atZoneSameInstant(ZoneOffset.UTC))
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    // TODO: Adjust hashCode for specific data type if needed (e.g., OffsetDateTime.withNano(0)
    //  .atZoneSameInstant(ZoneOffset.UTC))
    return Objects.hash(value);
  }
}