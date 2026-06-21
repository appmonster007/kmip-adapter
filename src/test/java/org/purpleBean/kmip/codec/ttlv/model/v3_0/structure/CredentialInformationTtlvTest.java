package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.CredentialInformation;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CredentialInformation Ttlv Serialization Tests")
class CredentialInformationTtlvTest extends AbstractTtlvSerializationTestSuite<CredentialInformation> {

    @Override
    public Class<CredentialInformation> type() {
        return CredentialInformation.class;
    }

    @Override
    public CredentialInformation createDefault() {
        return CredentialInformation.builder().build();
    }

    @Override
    public CredentialInformation createVariant() {
        return CredentialInformation.builder().build();
    }
}