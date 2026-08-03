package org.purpleBean.kmip.model.v2_1.type;

import java.time.OffsetDateTime;
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
 * KMIP RotateDate datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class RotateDate implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.ROTATE_DATE.inst();
  public static final EncodingType encodingType = EncodingType.DATE_TIME;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RotateDate.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RotateDate.class,
          RotateDate::of);
    }
  }

  @NonNull
  private final OffsetDateTime value;

  @Builder
  private RotateDate(@NonNull OffsetDateTime value) {
    this.value = value;
    validate();
  }

  public static RotateDate of(@NonNull OffsetDateTime value) {
    return new RotateDate(value);
  }

  public static RotateDate of(@NonNull AttributeName attributeName,
                              @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof OffsetDateTime value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return new RotateDate(value);
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
    return AttributeValue.ofDateTime(value);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RotateDate that = (RotateDate) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}