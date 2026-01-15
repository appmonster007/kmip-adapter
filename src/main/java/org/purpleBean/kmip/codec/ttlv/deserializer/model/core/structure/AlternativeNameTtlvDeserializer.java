package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.structure.AlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AlternativeNameTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<AlternativeName, AlternativeName.AlternativeNameBuilder> {

    public AlternativeNameTtlvDeserializer() {
        super(AlternativeName.kmipTag);
    }

    @Override
    protected AlternativeName.AlternativeNameBuilder createBuilder() {
        return AlternativeName.builder();
    }

    @Override
    protected void setValue(AlternativeName.AlternativeNameBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ALTERNATIVE_NAME_TYPE ->
                    builder.alternativeNameType(mapper.readValue(p, AlternativeNameType.class));
            case KmipTag.Standard.ALTERNATIVE_NAME_VALUE ->
                    builder.alternativeNameValue(mapper.readValue(p, AlternativeNameValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AlternativeName build(AlternativeName.AlternativeNameBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return AlternativeName.encodingType;
    }
}