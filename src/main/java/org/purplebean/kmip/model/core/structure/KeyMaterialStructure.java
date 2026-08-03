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
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP KeyMaterialStructure attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class KeyMaterialStructure implements KeyMaterial, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyMaterialStructure.class);
      KeyMaterial.register(spec, encodingType, null, KeyMaterialStructure.class,
          KeyMaterialStructure::of);
    }
  }

  @NonNull
  @Singular
  private final List<KmipDataType> values;

  @Builder
  private KeyMaterialStructure(List<KmipDataType> values) {
    this.values = (values == null) ? Collections.emptyList() : values;
    validate();
  }

  /**
   * Returns the {@link KeyMaterialStructure} instance wrapping the given value.
   */
  public static KeyMaterialStructure of(@NonNull KeyMaterial value) {
    if (!(value instanceof KeyMaterialStructure keyMaterialStructure)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    return keyMaterialStructure;
  }

  /**
   * Returns the {@link KeyMaterialStructure} instance wrapping the given value.
   */
  public static KeyMaterialStructure of(@NonNull List<KmipDataType> values) {
    return KeyMaterialStructure
        .builder()
        .values(values)
        .build();
  }

  /**
   * Returns the {@link KeyMaterialStructure} instance wrapping the given value.
   */
  public static KeyMaterialStructure of(@NonNull KmipDataType... values) {
    return of(List.of(values));
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
    return spec != KmipSpec.UnsupportedVersion && Stream
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
