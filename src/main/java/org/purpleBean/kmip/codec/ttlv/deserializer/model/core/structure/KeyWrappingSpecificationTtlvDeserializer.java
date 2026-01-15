package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyWrappingSpecificationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyWrappingSpecification, KeyWrappingSpecification.KeyWrappingSpecificationBuilder> {

    public KeyWrappingSpecificationTtlvDeserializer() {
        super(KeyWrappingSpecification.kmipTag);
    }

    @Override
    protected KeyWrappingSpecification.KeyWrappingSpecificationBuilder createBuilder() {
        return KeyWrappingSpecification.builder();
    }

    @Override
    protected void setValue(KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.WRAPPING_METHOD -> builder.wrappingMethod(mapper.readValue(p, WrappingMethod.class));
            case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION -> builder.encryptionKeyInformation(mapper.readValue(p, EncryptionKeyInformation.class));
            case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION -> builder.macSignatureKeyInformation(mapper.readValue(p, MACSignatureKeyInformation.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            case KmipTag.Standard.ENCODING_OPTION -> builder.encodingOption(mapper.readValue(p, EncodingOption.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected KeyWrappingSpecification build(KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return KeyWrappingSpecification.encodingType;
    }
}