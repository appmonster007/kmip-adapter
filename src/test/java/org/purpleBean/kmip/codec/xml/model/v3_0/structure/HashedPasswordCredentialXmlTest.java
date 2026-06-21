package org.purpleBean.kmip.codec.xml.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.HashedPasswordCredential;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashedPasswordCredential Xml Serialization Tests")
class HashedPasswordCredentialXmlTest extends AbstractXmlSerializationTestSuite<HashedPasswordCredential> {

    @Override
    public Class<HashedPasswordCredential> type() {
        return HashedPasswordCredential.class;
    }

    @Override
    public HashedPasswordCredential createDefault() {
        return HashedPasswordCredential.builder().build();
    }

    @Override
    public HashedPasswordCredential createVariant() {
        return HashedPasswordCredential.builder().build();
    }
}