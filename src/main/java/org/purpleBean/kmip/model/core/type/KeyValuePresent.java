package org.purpleBean.kmip.model.core.type;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.util.StringUtils;

/**
 * KMIP KeyValuePresent attribute.
 */
@Data
@Builder(toBuilder = true)
public class KeyValuePresent implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.KEY_VALUE_PRESENT.inst();
  public static final EncodingType encodingType = EncodingType.BOOLEAN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyValuePresent.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, KeyValuePresent.class,
          KeyValuePresent::of);
    }
  }

  @NonNull
  private final Boolean value;

  @Builder
  private KeyValuePresent(@NonNull Boolean value) {
    this.value = value;
    validate();
  }

  public static KeyValuePresent of(@NonNull Boolean value) {
    return new KeyValuePresent(value);
  }

  public static KeyValuePresent of(@NonNull AttributeName attributeName,
                                   @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof Boolean value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return KeyValuePresent
        .builder()
        .value(value)
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
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofBoolean(value);
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
    return false;
  }

  @Override
  public boolean isServerModifiable(@NonNull State state) {
    return false;
  }

  @Override
  public boolean isClientModifiable(@NonNull State state) {
    return false;
  }

  @Override
  public boolean isClientDeletable() {
    return false;
  }

  @Override
  public boolean isMultiInstanceAllowed() {
    return false;
  }
}
