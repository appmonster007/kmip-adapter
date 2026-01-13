package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.common.structure.Name;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NameTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Name, Name.NameBuilder> {

    public NameTtlvDeserializer() {
        super(Name.kmipTag);
    }

    @Override
    protected Name.NameBuilder createBuilder() {
        return Name.builder();
    }

    @Override
    protected void setValue(Name.NameBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NAME_VALUE -> builder.nameValue(mapper.readValue(p, NameValue.class));
            case KmipTag.Standard.NAME_TYPE -> builder.nameType(mapper.readValue(p, NameType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Name build(Name.NameBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Name.encodingType;
    }
}