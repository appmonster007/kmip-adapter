package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QString TTLV Serialization Tests")
class QStringTtlvTest extends AbstractTtlvSerializationTestSuite<QString> {

    @Override
    public Class<QString> type() {
        return QString.class;
    }

    @Override
    public QString createDefault() {
        return QString.of("test-qstring".getBytes());
    }

    @Override
    public QString createVariant() {
        return QString.of("another-qstring".getBytes());
    }
}