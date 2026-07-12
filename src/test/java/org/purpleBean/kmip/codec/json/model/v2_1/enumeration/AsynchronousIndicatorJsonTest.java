package org.purpleBean.kmip.codec.json.model.v2_1.enumeration;

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
import org.purpleBean.kmip.model.v2_1.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousIndicator Json Serialization Tests")
class AsynchronousIndicatorJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousIndicator> {

    @Override
    public Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    public AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.Standard.values()[0].inst();
    }

    @Override
    public AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.Standard.values()[1].inst();
    }
}