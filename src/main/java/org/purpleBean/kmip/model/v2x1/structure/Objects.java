package org.purplebean.kmip.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

@Data
@Builder(toBuilder = true)
public class Objects implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.OBJECTS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Objects.class);
    }
  }

  @NonNull
  @Singular("uniqueIdentifier")
  private final List<UniqueIdentifier> uniqueIdentifiers;

  @Builder
  private Objects(List<UniqueIdentifier> uniqueIdentifiers) {
    this.uniqueIdentifiers =
        (uniqueIdentifiers == null) ? Collections.emptyList() : uniqueIdentifiers;
    validate();
  }

  public static Objects of(@NonNull List<UniqueIdentifier> uniqueIdentifiers) {
    return Objects
        .builder()
        .uniqueIdentifiers(uniqueIdentifiers)
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
    return supportedVersions.contains(spec) && uniqueIdentifiers
        .stream()
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return uniqueIdentifiers
        .stream()
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
