package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialValueGenericStructureTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CredentialValueGenericStructure, CredentialValueGenericStructure.CredentialValueGenericStructureBuilder> {

    public CredentialValueGenericStructureTtlvDeserializer() {
        super(CredentialValueGenericStructure.kmipTag, CredentialValueGenericStructure.encodingType);
    }

    @Override
    protected CredentialValueGenericStructure.CredentialValueGenericStructureBuilder createBuilder() {
        return CredentialValueGenericStructure.builder();
    }

    @Override
    protected void setValue(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, KmipDataType.class));
    }

    @Override
    protected CredentialValueGenericStructure build(CredentialValueGenericStructure.CredentialValueGenericStructureBuilder builder) {
        return builder.build();
    }
}