package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QString TTLV Serialization Tests")
class QStringTtlvTest extends AbstractTtlvSerializationTestSuite<QString> {

    @Override
    protected Class<QString> type() {
        return QString.class;
    }

    @Override
    protected QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    protected QString createVariant() {
        return QString.of("another-qstring".getBytes());
    }
}