package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyWrappingDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyWrappingData, KeyWrappingData.KeyWrappingDataBuilder> {

    public KeyWrappingDataTtlvDeserializer() {
        super(KeyWrappingData.kmipTag, KeyWrappingData.encodingType);
    }

    @Override
    protected KeyWrappingData.KeyWrappingDataBuilder createBuilder() {
        return KeyWrappingData.builder();
    }

    @Override
    protected void setValue(KeyWrappingData.KeyWrappingDataBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.WRAPPING_METHOD -> builder.wrappingMethod(mapper.readValue(p, WrappingMethod.class));
            case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION ->
                    builder.encryptionKeyInformation(mapper.readValue(p, EncryptionKeyInformation.class));
            case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION ->
                    builder.macSignatureKeyInformation(mapper.readValue(p, MACSignatureKeyInformation.class));
            case KmipTag.Standard.MAC_SIGNATURE -> builder.macSignature(mapper.readValue(p, MACSignature.class));
            case KmipTag.Standard.IV_COUNTER_NONCE -> builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
            case KmipTag.Standard.ENCODING_OPTION -> builder.encodingOption(mapper.readValue(p, EncodingOption.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected KeyWrappingData build(KeyWrappingData.KeyWrappingDataBuilder builder) {
        return builder.build();
    }
}