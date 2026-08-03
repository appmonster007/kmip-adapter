package org.purplebean.kmip.model.v2x1.type;

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
 * KMIP TicketValue dataType (ByteString, tag 0x42014B, V2.1+).
 */
@Data
@Builder(toBuilder = true)
public class TicketValue implements KmipDataType {

  public static final KmipTag kmipTag = KmipTag.Standard.TICKET_VALUE.inst();
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, TicketValue.class);
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private TicketValue(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  public static TicketValue of(@NonNull ByteBuffer value) {
    return new TicketValue(value);
  }

  public static TicketValue of(byte[] value) {
    return TicketValue
        .builder()
        .value(ByteBuffer.wrap(value))
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
    return supportedVersions.contains(spec);
  }
}
