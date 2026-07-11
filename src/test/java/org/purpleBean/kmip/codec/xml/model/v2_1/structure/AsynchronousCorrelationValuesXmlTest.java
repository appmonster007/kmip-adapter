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
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousCorrelationValues;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValues Xml Serialization Tests")
class AsynchronousCorrelationValuesXmlTest extends AbstractXmlSerializationTestSuite<AsynchronousCorrelationValues> {

    @Override
    public Class<AsynchronousCorrelationValues> type() {
        return AsynchronousCorrelationValues.class;
    }

    @Override
    public AsynchronousCorrelationValues createDefault() {
        return AsynchronousCorrelationValues.builder().build();
    }

    @Override
    public AsynchronousCorrelationValues createVariant() {
        return AsynchronousCorrelationValues.builder().build();
    }
}