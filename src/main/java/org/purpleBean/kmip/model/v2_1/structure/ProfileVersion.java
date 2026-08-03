package org.purpleBean.kmip.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMajor;
import org.purpleBean.kmip.model.v2_1.type.ProfileVersionMinor;

@Data
@Builder(toBuilder = true)
public class ProfileVersion implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.PROFILE_VERSION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProfileVersion.class);
    }
  }

  @NonNull
  private final ProfileVersionMajor profileVersionMajor;
  @NonNull
  private final ProfileVersionMinor profileVersionMinor;

  @Builder
  private ProfileVersion(@NonNull ProfileVersionMajor profileVersionMajor,
                         @NonNull ProfileVersionMinor profileVersionMinor) {
    this.profileVersionMajor = profileVersionMajor;
    this.profileVersionMinor = profileVersionMinor;
    validate();
  }

  public static ProfileVersion of(@NonNull ProfileVersionMajor major,
                                  @NonNull ProfileVersionMinor minor) {
    return ProfileVersion
        .builder()
        .profileVersionMajor(major)
        .profileVersionMinor(minor)
        .build();
  }

  public static ProfileVersion of(int major, int minor) {
    return of(ProfileVersionMajor.of(major), ProfileVersionMinor.of(minor));
  }

  public static ProfileVersion of(@NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = ProfileVersion.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof ProfileVersionMajor m) {
        builder.profileVersionMajor(m);
      } else if (field instanceof ProfileVersionMinor m) {
        builder.profileVersionMinor(m);
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
        .of(profileVersionMajor, profileVersionMinor)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
