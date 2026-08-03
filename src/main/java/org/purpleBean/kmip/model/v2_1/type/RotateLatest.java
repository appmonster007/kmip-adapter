package org.purpleBean.kmip.model.v2_1.type;

import java.util.Objects;
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
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

/**
 * KMIP RotateLatest datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class RotateLatest implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.ROTATE_LATEST.inst();
  public static final EncodingType encodingType = EncodingType.BOOLEAN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RotateLatest.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RotateLatest.class,
          RotateLatest::of);
    }
  }

  @NonNull
  private final Boolean value;

  @Builder
  private RotateLatest(@NonNull Boolean value) {
    this.value = value;
    validate();
  }

  public static RotateLatest of(@NonNull Boolean value) {
    return new RotateLatest(value);
  }

  public static RotateLatest of(@NonNull AttributeName attributeName,
                                @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof Boolean value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return new RotateLatest(value);
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
    return true;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RotateLatest that = (RotateLatest) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}