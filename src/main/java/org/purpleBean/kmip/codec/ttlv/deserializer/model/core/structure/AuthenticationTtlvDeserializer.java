package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AuthenticationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Authentication, Authentication.AuthenticationBuilder> {

    public AuthenticationTtlvDeserializer() {
        super(Authentication.kmipTag, Authentication.encodingType);
    }

    @Override
    protected Authentication.AuthenticationBuilder createBuilder() {
        return Authentication.builder();
    }

    @Override
    protected void setValue(Authentication.AuthenticationBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        if (nodeTag == KmipTag.Standard.CREDENTIAL) {
            builder.credential(mapper.readValue(p, Credential.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Authentication build(Authentication.AuthenticationBuilder builder) {
        return builder.build();
    }
}