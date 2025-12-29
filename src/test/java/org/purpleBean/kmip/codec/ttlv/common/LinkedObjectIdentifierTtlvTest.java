package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("LinkedObjectIdentifier TTLV Serialization Tests")
class LinkedObjectIdentifierTtlvTest extends AbstractTtlvSerializationSuite<LinkedObjectIdentifier> {

    @Override
    protected Class<LinkedObjectIdentifier> type() {
        return LinkedObjectIdentifier.class;
    }

    @Override
    protected LinkedObjectIdentifier createDefault() {
        return LinkedObjectIdentifier.builder().value("test-linked-id").build();
    }

    @Override
    protected LinkedObjectIdentifier createVariant() {
        return LinkedObjectIdentifier.builder().value("another-linked-id").build();
    }
}