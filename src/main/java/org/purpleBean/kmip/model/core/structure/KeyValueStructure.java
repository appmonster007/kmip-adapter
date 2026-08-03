package org.purplebean.kmip.model.core.structure;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KeyValue;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP KeyValueStructure structure.
 */
@Data
@Builder(toBuilder = true)
public class KeyValueStructure implements KeyValue, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyValueStructure.class);
    }
  }

  @NonNull
  private final KeyMaterial keyMaterial;
  @NonNull
  @Singular
  private final List<KmipAttribute> attributes;

  @Builder
  private KeyValueStructure(@NonNull KeyMaterial keyMaterial, List<KmipAttribute> attributes) {
    this.keyMaterial = keyMaterial;
    this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
    validate();
  }

  public static KeyValueStructure of(@NonNull KeyMaterial keyMaterial,
                                     @NonNull List<KmipAttribute> attributes) {
    return KeyValueStructure
        .builder()
        .keyMaterial(keyMaterial)
        .attributes(attributes)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    List<KmipDataType> fields = Stream
        .concat(
            Stream.of(keyMaterial),
            attributes.stream()
        )
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    // Validate KMIP spec compatibility
    KmipSpec spec = KmipContext.getSpec();
    for (KmipDataType field : fields) {
      if (field != null && !field.isSupported()) {
        throw new IllegalArgumentException(
            String.format("%s is not supported for KMIP spec %s", field
                .getKmipTag()
                .getDescription(), spec)
        );
      }
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(keyMaterial, attributes)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
