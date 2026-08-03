package org.purpleBean.kmip.model.v2x1.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

@Data
@Builder(toBuilder = true)
public class ObjectDefaults implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.OBJECT_DEFAULTS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObjectDefaults.class);
    }
  }

  // Either objectTypes (plural wrapper) or objectType (singular) is set, not both
  private final ObjectTypes objectTypes;
  private final ObjectType objectType;
  @NonNull
  private final Attributes attributes;
  private final ObjectGroups objectGroups;

  @Builder
  private ObjectDefaults(ObjectTypes objectTypes, ObjectType objectType,
                         @NonNull Attributes attributes, ObjectGroups objectGroups) {
    this.objectTypes = objectTypes;
    this.objectType = objectType;
    this.attributes = attributes;
    this.objectGroups = objectGroups;
    validate();
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(objectTypes, objectType, attributes, objectGroups)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
