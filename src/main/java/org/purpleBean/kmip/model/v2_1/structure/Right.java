package org.purpleBean.kmip.model.v2_1.structure;

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
import org.purpleBean.kmip.model.core.structure.UsageLimits;

@Data
@Builder(toBuilder = true)
public class Right implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.RIGHT.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Right.class);
    }
  }

  private final UsageLimits usageLimits;
  private final Operations operations;
  private final org.purpleBean.kmip.model.v2_1.structure.Objects managedObjects;
  private final ObjectGroups objectGroups;

  @Builder
  private Right(UsageLimits usageLimits, Operations operations,
                org.purpleBean.kmip.model.v2_1.structure.Objects managedObjects,
                ObjectGroups objectGroups) {
    this.usageLimits = usageLimits;
    this.operations = operations;
    this.managedObjects = managedObjects;
    this.objectGroups = objectGroups;
    validate();
  }

  public static Right of(@NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = Right.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof UsageLimits u) {
        builder.usageLimits(u);
      } else if (field instanceof Operations o) {
        builder.operations(o);
      } else if (field instanceof org.purpleBean.kmip.model.v2_1.structure.Objects o) {
        builder.managedObjects(o);
      } else if (field instanceof ObjectGroups g) {
        builder.objectGroups(g);
      }
    }
    return builder.build();
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
        .of(usageLimits, operations, managedObjects, objectGroups)
        .filter(x -> x != null)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
