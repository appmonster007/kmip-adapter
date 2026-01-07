package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP KeyMaterial dataType.
 */
public abstract class KeyMaterial {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_MATERIAL);

    public interface Value extends KmipDataType {
    }

    @Data
    @Builder(toBuilder = true)
    public static class ByteString implements Value {

        public static final KmipTag kmipTag = KeyMaterial.kmipTag;
        public static final EncodingType encodingType = EncodingType.BYTE_STRING;
        private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, ByteString.class);
            }
        }

        @NonNull
        private final ByteBuffer value;

        public static ByteString of(@NonNull ByteBuffer value) {
            return ByteString.builder().value(value).build();
        }

        public static ByteString of(byte[] value) {
            return ByteString.builder().value(ByteBuffer.wrap(value)).build();
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
}
