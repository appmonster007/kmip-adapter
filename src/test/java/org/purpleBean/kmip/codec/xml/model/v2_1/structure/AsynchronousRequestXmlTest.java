package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousRequest Xml Serialization Tests")
class AsynchronousRequestXmlTest extends AbstractXmlSerializationTestSuite<AsynchronousRequest> {

    @Override
    public Class<AsynchronousRequest> type() {
        return AsynchronousRequest.class;
    }

    @Override
    public AsynchronousRequest createDefault() {
        return AsynchronousRequest.of(
                AsynchronousCorrelationValue.of(new byte[]{0x01}),
                Operation.Standard.CREATE.inst(),
                SubmissionDate.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
                ProcessingStage.Standard.SUBMITTED.inst());
    }

    @Override
    public AsynchronousRequest createVariant() {
        return AsynchronousRequest.of(
                AsynchronousCorrelationValue.of(new byte[]{0x02}),
                Operation.Standard.GET.inst(),
                SubmissionDate.of(OffsetDateTime.of(2024, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
                ProcessingStage.Standard.SUBMITTED.inst());
    }
}