package org.purplebean.kmip.model.v2x1.type;

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
 * KMIP Pkcs12FriendlyName datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs12FriendlyName implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.PKCS_12_FRIENDLY_NAME.inst();
  public static final EncodingType encodingType = EncodingType.TEXT_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs12FriendlyName.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Pkcs12FriendlyName.class,
          Pkcs12FriendlyName::of);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private Pkcs12FriendlyName(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link Pkcs12FriendlyName} instance wrapping the given value.
   */
  public static Pkcs12FriendlyName of(@NonNull String value) {
    return new Pkcs12FriendlyName(value);
  }

  /**
   * Returns the {@link Pkcs12FriendlyName} instance wrapping the given value.
   */
  public static Pkcs12FriendlyName of(@NonNull AttributeName attributeName,
                                      @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return new Pkcs12FriendlyName(value);
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
    return true;
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
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pkcs12FriendlyName that = (Pkcs12FriendlyName) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}