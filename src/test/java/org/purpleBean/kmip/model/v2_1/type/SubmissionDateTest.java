package org.purpleBean.kmip.model.v2_1.type;

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
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("SubmissionDate Domain Tests")
class SubmissionDateTest extends AbstractKmipDataTypeTestSuite<SubmissionDate> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<SubmissionDate> type() {
        return SubmissionDate.class;
    }

    @Override
    protected SubmissionDate createDefault() {
        return SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME_EXTENDED;
    }
}