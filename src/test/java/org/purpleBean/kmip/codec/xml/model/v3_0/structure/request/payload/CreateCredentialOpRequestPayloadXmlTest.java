package org.purpleBean.kmip.codec.xml.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateCredentialOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateCredentialOpRequestPayload Xml Serialization Tests")
class CreateCredentialOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateCredentialOpRequestPayload> {

    @Override
    public Class<CreateCredentialOpRequestPayload> type() {
        return CreateCredentialOpRequestPayload.class;
    }
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }



    @Override
    public CreateCredentialOpRequestPayload createDefault() {
        return CreateCredentialOpRequestPayload.builder().credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst()).credentialValue(UsernameAndPassword.of("user", "pass")).build();
    }

    @Override
    public CreateCredentialOpRequestPayload createVariant() {
        return CreateCredentialOpRequestPayload.builder().credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst()).credentialValue(UsernameAndPassword.of("admin", "secret")).build();
    }
}