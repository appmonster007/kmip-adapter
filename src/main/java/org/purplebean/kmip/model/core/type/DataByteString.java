package org.purplebean.kmip.model.core.type;

import java.nio.ByteBuffer;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * KMIP DataByteString dataType.
 */
@Data
@Builder(toBuilder = true)
public class DataByteString implements org.purplebean.kmip.api.DataValue {

  public static final KmipTag kmipTag = KmipTag.Standard.DATA.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DataByteString.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private DataByteString(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link DataByteString} instance wrapping the given value.
   */
  public static DataByteString of(@NonNull ByteBuffer value) {
    return new DataByteString(value);
  }

  /**
   * Returns the {@link DataByteString} instance wrapping the given value.
   */
  public static DataByteString of(byte[] value) {
    return DataByteString
        .builder()
        .value(ByteBuffer.wrap(value))
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
}
