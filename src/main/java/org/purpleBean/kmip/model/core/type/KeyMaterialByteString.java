package org.purpleBean.kmip.model.core.type;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

@Data
@Builder(toBuilder = true)
public class KeyMaterialByteString implements KeyMaterial {

  public static final KmipTag kmipTag = KeyMaterial.kmipTag;
  public static final EncodingType encodingType = EncodingType.BYTE_STRING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    ArrayList<KeyFormatType.Value> byteStringKeyFormatTypes = new ArrayList<>(Arrays.asList(
        null, KeyFormatType.Standard.RAW, KeyFormatType.Standard.OPAQUE,
        KeyFormatType.Standard.PKCS_1, KeyFormatType.Standard.PKCS_8,
        KeyFormatType.Standard.EC_PRIVATE_KEY, KeyFormatType.Standard.X_509,
        KeyFormatType.Standard.PKCS_12
    ));
    byteStringKeyFormatTypes.addAll(KeyFormatType.registeredValues());

    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyMaterialByteString.class);
      for (KeyFormatType.Value keyFormatType : byteStringKeyFormatTypes) {
        KeyMaterial.register(spec, encodingType, keyFormatType, KeyMaterialByteString.class,
            KeyMaterialByteString::of);
      }
    }
  }

  @NonNull
  private final ByteBuffer value;

  @Builder
  private KeyMaterialByteString(@NonNull ByteBuffer value) {
    this.value = value;
    validate();
  }

  public static KeyMaterialByteString of(@NonNull KeyMaterial value) {
    if (!(value instanceof KeyMaterialByteString byteString)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    return new KeyMaterialByteString(byteString.getValue());
  }

  public static KeyMaterialByteString of(@NonNull ByteBuffer value) {
    return new KeyMaterialByteString(value);
  }

  public static KeyMaterialByteString of(byte[] value) {
    return KeyMaterialByteString
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
