package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v3_0.structure.link.Pkcs12PasswordLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs12PasswordLink Ttlv Serialization Tests")
class Pkcs12PasswordLinkTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs12PasswordLink> {

    @Override
    public Class<Pkcs12PasswordLink> type() {
        return Pkcs12PasswordLink.class;
    }

    @Override
    public Pkcs12PasswordLink createDefault() {
        return Pkcs12PasswordLink.builder().build();
    }

    @Override
    public Pkcs12PasswordLink createVariant() {
        return Pkcs12PasswordLink.builder().build();
    }
}