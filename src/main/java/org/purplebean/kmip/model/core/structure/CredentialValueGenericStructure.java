package org.purplebean.kmip.model.core.structure;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.CredentialValue;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP CredentialValueGenericStructure attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class CredentialValueGenericStructure implements CredentialValue, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CredentialValueGenericStructure.class);
      CredentialValue.register(spec, encodingType, null, CredentialValueGenericStructure.class,
          CredentialValueGenericStructure::of);
    }
  }

  @NonNull
  @Singular
  private final List<KmipDataType> values;

  @Builder
  private CredentialValueGenericStructure(
      List<KmipDataType> values
  ) {
    this.values = (values == null) ? Collections.emptyList() : values;
    validate();
  }

  /**
   * Returns the {@link CredentialValueGenericStructure} instance wrapping the given value.
   */
  public static CredentialValueGenericStructure of(KmipDataType... values) {
    return of(List.of(values));
  }

  /**
   * Returns the {@link CredentialValueGenericStructure} instance wrapping the given value.
   */
  public static CredentialValueGenericStructure of(
      List<KmipDataType> values
  ) {
    return CredentialValueGenericStructure
        .builder()
        .values(values)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    Objects.requireNonNull(values, "Values cannot be null");
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return values
        .stream()
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
