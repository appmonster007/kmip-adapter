package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3_0.structure.PasswordCredential;

import java.io.IOException;
import org.purpleBean.kmip.model.v3_0.type.PasswordSalt;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;

public class PasswordCredentialJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PasswordCredential, PasswordCredential.PasswordCredentialBuilder> {

    public PasswordCredentialJsonDeserializer() {
        super(PasswordCredential.kmipTag, PasswordCredential.encodingType);
    }

    @Override
    protected PasswordCredential.PasswordCredentialBuilder createBuilder() {
        return PasswordCredential.builder();
    }

    @Override
    protected void setValue(PasswordCredential.PasswordCredentialBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag == Password.kmipTag.getValue()) {
            builder.password(ctxt.readValue(p, Password.class));
        } else if (nodeTag == PasswordSalt.kmipTag.getValue()) {
            builder.passwordSalt(ctxt.readValue(p, PasswordSalt.class));
        } else if (nodeTag == PasswordSaltAlgorithm.kmipTag.getValue()) {
            builder.passwordSaltAlgorithm(ctxt.readValue(p, PasswordSaltAlgorithm.class));
        } else if (nodeTag == SaltedPassword.kmipTag.getValue()) {
            builder.saltedPassword(ctxt.readValue(p, SaltedPassword.class));
        } else if (nodeTag == IterationCount.kmipTag.getValue()) {
            builder.iterationCount(ctxt.readValue(p, IterationCount.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PasswordCredential build(PasswordCredential.PasswordCredentialBuilder builder) {
        return builder.build();
    }
}