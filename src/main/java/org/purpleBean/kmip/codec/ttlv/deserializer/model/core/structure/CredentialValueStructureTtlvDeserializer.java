package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CredentialValueStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialValueStructureTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CredentialValueStructure, CredentialValueStructure.CredentialValueStructureBuilder> {

    public CredentialValueStructureTtlvDeserializer() {
        super(CredentialValueStructure.kmipTag);
    }

    @Override
    protected CredentialValueStructure.CredentialValueStructureBuilder createBuilder() {
        return CredentialValueStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueStructure.CredentialValueStructureBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueStructure build(CredentialValueStructure.CredentialValueStructureBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CredentialValueStructure.encodingType;
    }
}