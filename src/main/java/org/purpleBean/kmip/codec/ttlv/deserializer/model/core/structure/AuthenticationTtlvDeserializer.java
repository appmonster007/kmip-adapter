package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AuthenticationTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Authentication, Authentication.AuthenticationBuilder> {

    public AuthenticationTtlvDeserializer() {
        super(Authentication.kmipTag);
    }

    @Override
    protected Authentication.AuthenticationBuilder createBuilder() {
        return Authentication.builder();
    }

    @Override
    protected void setValue(Authentication.AuthenticationBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL -> builder.credential(mapper.readValue(p, Credential.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Authentication build(Authentication.AuthenticationBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Authentication.encodingType;
    }
}