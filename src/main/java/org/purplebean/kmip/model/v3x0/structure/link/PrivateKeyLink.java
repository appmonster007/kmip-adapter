package org.purplebean.kmip.model.v3x0.structure.link;

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
 * KMIP {@code Private Key Link} attribute (KMIP 3.0 §4.35.15, tag {@code 0x420199}).
 *
 * <p>Expresses an association from a Public Key to its associated Private Key. Wire-encoded
 * directly on its own tag as a {@code Reference} (or {@code NameReference}/{@code
 * UniqueIdentifier}) value — NOT as a structure wrapping a nested {@code UniqueIdentifier} child.
 */
@Data
@Builder(toBuilder = true)
public class PrivateKeyLink implements KmipDataType, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY_LINK.inst();
  public static final EncodingType encodingType = EncodingType.REFERENCE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKeyLink.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, PrivateKeyLink.class,
          PrivateKeyLink::of);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private PrivateKeyLink(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link PrivateKeyLink} instance wrapping the given value.
   */
  public static PrivateKeyLink of(@NonNull String value) {
    return new PrivateKeyLink(value);
  }

  /**
   * Returns the {@link PrivateKeyLink} instance wrapping the given value.
   */
  public static PrivateKeyLink of(@NonNull AttributeName attributeName,
                                  @NonNull AttributeValue attributeValue) {
    if (!(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return PrivateKeyLink
        .builder()
        .value(value)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    return false;
  }

  @Override
  public AttributeValue getAttributeValue() {
    return AttributeValue
        .builder()
        .encodingType(encodingType)
        .value(value)
        .build();
  }

  @Override
  public AttributeName getAttributeName() {
    return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
  }

  @Override
  public String getCanonicalName() {
    return kmipTag.getDescription();
  }
}
