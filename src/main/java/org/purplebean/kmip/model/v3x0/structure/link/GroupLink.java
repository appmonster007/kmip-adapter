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
 * KMIP {@code Group Link} attribute (KMIP 3.0 §4.35.7, tag {@code 0x4201B3}).
 *
 * <p>Identifies the group that a managed object is a member of. Wire-encoded directly on its own
 * tag as a {@code NameReference} (or {@code Reference}/{@code UniqueIdentifier}) value — NOT as
 * a structure wrapping a nested {@code UniqueIdentifier} child.
 */
@Data
@Builder(toBuilder = true)
public class GroupLink implements KmipDataType, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.GROUP_LINK.inst();
  public static final EncodingType encodingType = EncodingType.NAME_REFERENCE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, GroupLink.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, GroupLink.class,
          GroupLink::of);
    }
  }

  @NonNull
  private final String value;

  @Builder
  private GroupLink(@NonNull String value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link GroupLink} instance wrapping the given value.
   */
  public static GroupLink of(@NonNull String value) {
    return new GroupLink(value);
  }

  /**
   * Returns the {@link GroupLink} instance wrapping the given value.
   */
  public static GroupLink of(@NonNull AttributeName attributeName,
                             @NonNull AttributeValue attributeValue) {
    if (!(attributeValue.getValue() instanceof String value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return GroupLink
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
    return true;
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
