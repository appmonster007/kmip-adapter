package org.purplebean.kmip.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
import org.purplebean.kmip.util.StringUtils;

/**
 * KMIP ProcessStartDate attribute.
 */
@Data
@Builder(toBuilder = true)
public class ProcessStartDate implements KmipDataType, KmipAttribute {
  public static final KmipTag kmipTag = KmipTag.Standard.PROCESS_START_DATE.inst();
  public static final EncodingType encodingType = EncodingType.DATE_TIME;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProcessStartDate.class);
      KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ProcessStartDate.class,
          ProcessStartDate::of);
    }
  }

  @NonNull
  private final OffsetDateTime value;

  @Builder
  private ProcessStartDate(@NonNull OffsetDateTime value) {
    this.value = value;
    validate();
  }

  public static ProcessStartDate of(@NonNull OffsetDateTime value) {
    return new ProcessStartDate(value);
  }

  public static ProcessStartDate of(@NonNull AttributeName attributeName,
                                    @NonNull AttributeValue attributeValue) {
    if (attributeValue.getEncodingType() != encodingType ||
        !(attributeValue.getValue() instanceof OffsetDateTime value)) {
      throw new IllegalArgumentException("Invalid attribute value");
    }
    return ProcessStartDate
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
  public boolean isClientModifiable(@NonNull State state) {
    return (
        state.getIntValue() == State.Standard.PRE_ACTIVE.getValue()
            || state.getIntValue() == State.Standard.ACTIVE.getValue()
    ) && value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC)
        .isAfter(OffsetDateTime
            .now()
            .withNano(0)
            .atZoneSameInstant(ZoneOffset.UTC));
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
    return (
        state.getIntValue() == State.Standard.PRE_ACTIVE.getValue()
            || state.getIntValue() == State.Standard.ACTIVE.getValue()
    ) && value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC)
        .isAfter(OffsetDateTime
            .now()
            .withNano(0)
            .atZoneSameInstant(ZoneOffset.UTC));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessStartDate that = (ProcessStartDate) o;
    // Compare OffsetDateTime up to seconds to avoid flakiness
    return this.value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC)
        .equals(that.value
            .withNano(0)
            .atZoneSameInstant(ZoneOffset.UTC));
  }

  @Override
  public int hashCode() {
    return Objects.hash(value
        .withNano(0)
        .atZoneSameInstant(ZoneOffset.UTC));
  }
}
