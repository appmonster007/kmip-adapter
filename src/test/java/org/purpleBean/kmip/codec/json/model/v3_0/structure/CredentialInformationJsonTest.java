package org.purpleBean.kmip.codec.json.model.v3_0.structure;

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
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CredentialInformation Json Serialization Tests")
class CredentialInformationJsonTest extends AbstractJsonSerializationTestSuite<CredentialInformation> {

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