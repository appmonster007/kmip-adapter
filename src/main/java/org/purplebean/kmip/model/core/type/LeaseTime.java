package org.purplebean.kmip.model.core.type;

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
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP LeaseTime dataType.
 */
@Data
@Builder(toBuilder = true)
public class LeaseTime implements KmipDataType, KmipAttribute {

  public static final KmipTag kmipTag = KmipTag.Standard.LEASE_TIME.inst();
  public static final EncodingType encodingType = EncodingType.INTERVAL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, LeaseTime.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, LeaseTime.class,
          LeaseTime::of);
    }
  }

  @NonNull
  private final Integer value;

  @Builder
  private LeaseTime(@NonNull Integer value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link LeaseTime} instance wrapping the given value.
   */
  public static LeaseTime of(@NonNull Integer value) {
    return new LeaseTime(value);
  }

  /**
   * Returns the {@link LeaseTime} instance wrapping the given value.
   */
  public static LeaseTime of(@NonNull AttributeName attributeName,
                             @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof Integer value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return LeaseTime
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
  public boolean isServerModifiable(State state) {
    return true;
  }

  @Override
  public boolean isClientModifiable(State state) {
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
  public AttributeValue getAttributeValue() {
    return AttributeValue.ofInterval(value);
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
