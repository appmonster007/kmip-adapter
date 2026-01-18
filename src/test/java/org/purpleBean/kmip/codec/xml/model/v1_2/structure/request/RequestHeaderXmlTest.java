package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestHeader;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RequestHeader Xml Serialization Tests")
class RequestHeaderXmlTest extends AbstractXmlSerializationTestSuite<RequestHeader> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RequestHeader> type() {
        return RequestHeader.class;
    }

    @Override
    protected RequestHeader createDefault() {
        return RequestHeader.builder()
                .protocolVersion(ProtocolVersion.builder()
                        .protocolVersionMajor(ProtocolVersionMajor.of(1))
                        .protocolVersionMinor(ProtocolVersionMinor.of(2))
                        .build())
                .batchCount(BatchCount.of(1))
                .build();
    }

    @Override
    protected RequestHeader createVariant() {
        return RequestHeader.builder()
                .protocolVersion(ProtocolVersion.builder()
                        .protocolVersionMajor(ProtocolVersionMajor.of(2))
                        .protocolVersionMinor(ProtocolVersionMinor.of(0))
                        .build())
                .batchCount(BatchCount.of(2))
                .build();
    }
}