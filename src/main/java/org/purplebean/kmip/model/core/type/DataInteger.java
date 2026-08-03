package org.purplebean.kmip.model.core.type;

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
 * KMIP {@code Data} value encoded as an Integer batch-item index.
 * <p>
 * KMIP 2.1 §11.13 allows the {@code Data} tag ({@code 0x4200C2}) to reference the output of
 * another batch item within a streaming batch by providing an integer index rather than raw bytes,
 * e.g. {@code <Data type="Integer" value="0"/>}.
 * <p>
 * Sibling of {@link DataByteString} (byte-string data) and
 * {@link org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration} (named placeholder).
 */
@Data
@Builder(toBuilder = true)
public class DataInteger implements org.purplebean.kmip.api.DataValue {

  public static final KmipTag kmipTag = KmipTag.Standard.DATA.inst();
  public static final EncodingType encodingType = EncodingType.INTEGER;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DataInteger.class);
    }
  }

  @NonNull
  private final Integer value;

  @Builder
  private DataInteger(@NonNull Integer value) {
    this.value = value;
    validate();
  }

  /**
   * Returns the {@link DataInteger} instance wrapping the given value.
   */
  public static DataInteger of(@NonNull Integer value) {
    return new DataInteger(value);
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
