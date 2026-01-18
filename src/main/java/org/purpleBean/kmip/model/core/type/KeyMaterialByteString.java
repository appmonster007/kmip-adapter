package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class KeyMaterialByteString implements KeyMaterial {

    public static final KmipTag kmipTag = KeyMaterial.kmipTag;
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        ArrayList<KeyFormatType.Value> byteStringKeyFormatTypes = new ArrayList<>(Arrays.asList(
                null, KeyFormatType.Standard.RAW, KeyFormatType.Standard.OPAQUE,
                KeyFormatType.Standard.PKCS_1, KeyFormatType.Standard.PKCS_8,
                KeyFormatType.Standard.EC_PRIVATE_KEY
        ));
        byteStringKeyFormatTypes.addAll(KeyFormatType.registeredValues());

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyMaterialByteString.class);
            for (KeyFormatType.Value keyFormatType : byteStringKeyFormatTypes) {
                KeyMaterial.register(spec, encodingType, keyFormatType, KeyMaterialByteString.class, KeyMaterialByteString::of);
            }
        }
    }

    @NonNull
    @Builder.Default
    private final KeyFormatType keyFormatType = KeyFormatType.Standard.RAW.inst();
    @NonNull
    private final ByteBuffer value;

    public static KeyMaterialByteString of(@NonNull KeyMaterial value) {
        if (!(value instanceof KeyMaterialByteString byteString)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        return byteString;
    }

    public static KeyMaterialByteString of(@NonNull ByteBuffer value) {
        return KeyMaterialByteString.builder().value(value).build();
    }

    public static KeyMaterialByteString of(byte[] value) {
        return KeyMaterialByteString.builder().value(ByteBuffer.wrap(value)).build();
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
