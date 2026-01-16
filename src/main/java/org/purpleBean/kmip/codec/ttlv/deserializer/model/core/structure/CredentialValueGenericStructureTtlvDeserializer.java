package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialValueGenericStructureTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CredentialValueGenericStructure, CredentialValueGenericStructure.CredentialValueGenericStructureBuilder> {

    public CredentialValueGenericStructureTtlvDeserializer() {
        super(CredentialValueGenericStructure.kmipTag);
    }

    @Override
    protected CredentialValueGenericStructure.CredentialValueGenericStructureBuilder createBuilder() {
        return CredentialValueGenericStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueGenericStructure build(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CredentialValueGenericStructure.encodingType;
    }
}