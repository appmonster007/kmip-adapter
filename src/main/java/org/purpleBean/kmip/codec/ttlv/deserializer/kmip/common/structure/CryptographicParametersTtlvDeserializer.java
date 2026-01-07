package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CryptographicParametersTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CryptographicParameters> {
    private final KmipTag kmipTag = CryptographicParameters.kmipTag;
    private final EncodingType encodingType = CryptographicParameters.encodingType;

    @Override
    public CryptographicParameters deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        CryptographicParameters.CryptographicParametersBuilder builder = CryptographicParameters.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        CryptographicParameters cryptographicparameters = builder.build();
        if (!cryptographicparameters.isSupported()) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", cryptographicparameters.getClass().getSimpleName(), spec));
        }
        return cryptographicparameters;
    }

    private void setValue(
            CryptographicParameters.CryptographicParametersBuilder builder,
            KmipTag.Value nodeTag,
            TtlvObject ttlvObject,
            TtlvMapper mapper
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.BLOCK_CIPHER_MODE ->
                    builder.blockCipherMode(mapper.readValue(ttlvObject.toByteBuffer(), BlockCipherMode.class));
            case KmipTag.Standard.PADDING_METHOD ->
                    builder.paddingMethod(mapper.readValue(ttlvObject.toByteBuffer(), PaddingMethod.class));
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(mapper.readValue(ttlvObject.toByteBuffer(), HashingAlgorithm.class));
            case KmipTag.Standard.KEY_ROLE_TYPE ->
                    builder.keyRoleType(mapper.readValue(ttlvObject.toByteBuffer(), KeyRoleType.class));
            case KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM ->
                    builder.digitalSignatureAlgorithm(mapper.readValue(ttlvObject.toByteBuffer(), DigitalSignatureAlgorithm.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
                    builder.cryptographicAlgorithm(mapper.readValue(ttlvObject.toByteBuffer(), CryptographicAlgorithm.class));
            case KmipTag.Standard.RANDOM_IV ->
                    builder.randomIv(mapper.readValue(ttlvObject.toByteBuffer(), RandomIv.class));
            case KmipTag.Standard.IV_LENGTH ->
                    builder.ivLength(mapper.readValue(ttlvObject.toByteBuffer(), IvLength.class));
            case KmipTag.Standard.TAG_LENGTH ->
                    builder.tagLength(mapper.readValue(ttlvObject.toByteBuffer(), TagLength.class));
            case KmipTag.Standard.FIXED_FIELD_LENGTH ->
                    builder.fixedFieldLength(mapper.readValue(ttlvObject.toByteBuffer(), FixedFieldLength.class));
            case KmipTag.Standard.INVOCATION_FIELD_LENGTH ->
                    builder.invocationFieldLength(mapper.readValue(ttlvObject.toByteBuffer(), InvocationFieldLength.class));
            case KmipTag.Standard.COUNTER_LENGTH ->
                    builder.counterLength(mapper.readValue(ttlvObject.toByteBuffer(), CounterLength.class));
            case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
                    builder.initialCounterValue(mapper.readValue(ttlvObject.toByteBuffer(), InitialCounterValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}