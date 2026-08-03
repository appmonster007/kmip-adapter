package org.purpleBean.kmip.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

@Data
@Builder(toBuilder = true)
public class ObjectGroups implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.OBJECT_GROUPS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObjectGroups.class);
    }
  }

  @NonNull
  @Singular("objectGroup")
  private final List<ObjectGroup> objectGroups;

  @Builder
  private ObjectGroups(List<ObjectGroup> objectGroups) {
    this.objectGroups = (objectGroups == null) ? Collections.emptyList() : objectGroups;
    validate();
  }

  public static ObjectGroups of(@NonNull List<ObjectGroup> objectGroups) {
    return ObjectGroups
        .builder()
        .objectGroups(objectGroups)
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
    return supportedVersions.contains(spec) && objectGroups
        .stream()
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return objectGroups
        .stream()
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
