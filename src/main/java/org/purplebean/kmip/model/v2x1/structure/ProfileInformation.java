package org.purplebean.kmip.model.v2x1.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.ProfileName;
import org.purplebean.kmip.model.v2x1.type.ServerPort;
import org.purplebean.kmip.model.v2x1.type.ServerUri;

/**
 * KMIP ProfileInformation structure.
 */
@Data
@Builder(toBuilder = true)
public class ProfileInformation implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.PROFILE_INFORMATION.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProfileInformation.class);
    }
  }

  @NonNull
  private final ProfileName profileName;
  private final ProfileVersion profileVersion;
  private final ServerUri serverUri;
  private final ServerPort serverPort;

  @Builder
  private ProfileInformation(@NonNull ProfileName profileName, ProfileVersion profileVersion,
                             ServerUri serverUri, ServerPort serverPort) {
    this.profileName = profileName;
    this.profileVersion = profileVersion;
    this.serverUri = serverUri;
    this.serverPort = serverPort;
    validate();
  }

  /**
   * Returns the {@link ProfileInformation} instance wrapping the given value.
   */
  public static ProfileInformation of(@NonNull ProfileName profileName) {
    return ProfileInformation
        .builder()
        .profileName(profileName)
        .build();
  }

  /**
   * Returns the {@link ProfileInformation} instance wrapping the given value.
   */
  public static ProfileInformation of(@NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = ProfileInformation.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof ProfileName n) {
        builder.profileName(n);
      } else if (field instanceof ProfileVersion v) {
        builder.profileVersion(v);
      } else if (field instanceof ServerUri u) {
        builder.serverUri(u);
      } else if (field instanceof ServerPort p) {
        builder.serverPort(p);
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
        .of(profileName, profileVersion, serverUri, serverPort)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
